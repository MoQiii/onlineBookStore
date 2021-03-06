package com.syj.olb.oldbook.web;

import com.syj.olb.attachments.pojo.Attachments;
import com.syj.olb.attachments.service.AttachmentsService;
import com.syj.olb.book.pojo.Book;
import com.syj.olb.book.pojo.PageBean;
import com.syj.olb.category.pojo.Category;
import com.syj.olb.category.service.CategoryService;
import com.syj.olb.oldbook.pojo.OldBook;
import com.syj.olb.oldbook.pojo.OldBookQuery;
import com.syj.olb.oldbook.service.OldBookService;
import com.syj.olb.user.pojo.User;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RequestMapping("/OldBook")
@Controller
//@ConfigurationProperties(prefix = "attachment")
//@PropertySource("config/attachments.properties")
public class OldBookController {

    @Resource(name="oldBookServiceImpl")
    private OldBookService bookService;
    /*@Value("${attachment.filePath}")
    private String filePath;
    @Value("${attachment.fileUrl}")
    private String fileUrl;*/
    @Resource(name="categoryServiceImpl")
    private CategoryService categoryService;
    @Resource(name="AttachmentsServiceImpl")
    private AttachmentsService attachmentsService;
    @RequestMapping("/welcome")
    public  String welcome(Model model){

        return "jsps/oldBookExchange/main";
    }
    @RequestMapping("/addView")
    public String addView(HttpServletRequest req, HttpServletResponse res, OldBook oldBook){

        return "jsps/oldBookExchange/addOldBook";
    }
    @RequestMapping(value="/delete",method = RequestMethod.POST)
    public String deleteOldBook(HttpServletRequest req, HttpServletResponse res){
        String bid = req.getParameter("bid");
        bookService.delete(bid);
        return "redirect:/OldBook/oldBookList";
    }
    @RequestMapping("/oldBookList")
    public String oldBookList(HttpServletRequest req, HttpServletResponse res,Model model){
        Object sessionUser = req.getSession().getAttribute("sessionUser");
        User user=new User();
        if(Objects.nonNull(sessionUser)){
            user= (User)sessionUser;
        }
        List<Book> oldBooks = bookService.oldBookList(user.getUid());
        PageBean<Book> oldBookPageBean = new PageBean<>();
        oldBookPageBean.setPc(1);
        oldBookPageBean.setTr(oldBooks.size());
        oldBookPageBean.setPs(100);
        oldBookPageBean.setBeanList(oldBooks);
        model.addAttribute("pb",oldBookPageBean);
        model.addAttribute("flag","old");
        return "jsps/oldBookExchange/list";
    }
    @RequestMapping("/add")
    public String addOldBook(HttpServletRequest req, HttpServletResponse res,OldBook oldBook,@RequestParam("file") MultipartFile file) throws InvocationTargetException, IllegalAccessException, IOException {
        //获取主键
        UUID uuid = UUID.randomUUID();
        User user=new User();
        //判断用户是否登录
        Object object = req.getSession().getAttribute("sessionUser");
        if(object==null){
            return "redirect:/user/loginToView";
        }
        else{
            user=(User)object;
        }
        //把上传的书籍信息封装进对象oldBook
        BeanUtils.populate(oldBook,req.getParameterMap());
        String childCategory = req.getParameter("childCategory");
        Category categoryByCname = categoryService.findCategoryByCname(childCategory);
        oldBook.setCategory(categoryByCname);
        Attachments attachments = new Attachments();
        attachments.setBusiId(uuid.toString().substring(0,10));
        attachments.setBusiType("oldBookPic");
        //读取文件内容，并保存到图片服务器
        attachmentsService.insertAttachments(attachments,file,user);
        oldBook.setImage_b(attachments.getFileUrl());
        oldBook.setImage_w(attachments.getFileUrl());
        //  FileInputStream fis=new FileInputStream(file.getInputStream());
        oldBook.setBid(uuid.toString().substring(0,10));
        oldBook.setUid(user.getUid());
        bookService.add(oldBook);
        return "redirect:/OldBook/welcome?message=上架成功";
    }
    /**
     * 获取当前页码
     * @param req
     * @return
     */
    private int getPc(HttpServletRequest req) {
        int pc = 1;
        String param = req.getParameter("pc");
        if(param != null && !param.trim().isEmpty()) {
            try {
                pc = Integer.parseInt(param);
            } catch(RuntimeException e) {}
        }
        return pc;
    }
    /**
     * 截取url，页面中的分页导航中需要使用它做为超链接的目标！
     * @param req
     * @return
     */
    /*
     * http://localhost:8080/goods/BookServlet?methed=findByCategory&cid=xxx&pc=3
     * /goods/BookServlet + methed=findByCategory&cid=xxx&pc=3
     */
    private String getUrl(HttpServletRequest req) {
        String url = req.getRequestURI() + "?" + req.getQueryString();
        /*
         * 如果url中存在pc参数，截取掉，如果不存在那就不用截取。
         */
        int index = url.lastIndexOf("&pc=");
        if(index != -1) {
            url = url.substring(0, index);
        }
        return url;
    }
    /**
     * 按bid查询
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/load")
    public String load(HttpServletRequest req, HttpServletResponse resp){
        String bid = req.getParameter("bid");//获取链接的参数bid
        Book book = bookService.load(bid);//通过bid得到book对象
        req.setAttribute("book", book);//保存到req中
        return "jsps/oldBookExchange/desc";//转发到desc.jsp
    }

    /**
     * 按分类查
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/findByCategory")
    public String findByCategory(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        /*
         * 1. 得到pc：如果页面传递，使用页面的，如果没传，pc=1
         */
        int pc = getPc(req);
        /*
         * 2. 得到url：...
         */
        String url = getUrl(req);
        /*
         * 3. 获取查询条件，本方法就是cid，即分类的id
         */
        String cid = req.getParameter("cid");
        OldBookQuery bookQuery = new OldBookQuery();
        bookQuery.setCid(cid);
        bookQuery.setPc(pc);
        bookQuery.setStatus("-1");
        /*
         * 4. 使用pc和cid调用service#findByCategory得到PageBean
         */
        PageBean<Book> pb = bookService.findByCategory(bookQuery);
        /*
         * 5. 给PageBean设置url，保存PageBean，转发到/jsps/book/list.jsp
         */
        pb.setUrl(url);
        req.setAttribute("pb", pb);
        return "jsps/oldBookExchange/list";
    }

    /**
     * 按作者查
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String findByAuthor(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        /*
         * 1. 得到pc：如果页面传递，使用页面的，如果没传，pc=1
         */
        int pc = getPc(req);
        /*
         * 2. 得到url：...
         */
        String url = getUrl(req);
        /*
         * 3. 获取查询条件，本方法就是cid，即分类的id
         */
        String author = req.getParameter("author");
        /*
         * 4. 使用pc和cid调用service#findByCategory得到PageBean
         */
        PageBean<OldBook> pb = bookService.findByAuthor(author, pc);
        /*
         * 5. 给PageBean设置url，保存PageBean，转发到/jsps/book/list.jsp
         */
        pb.setUrl(url);
        req.setAttribute("pb", pb);
        return "jsps/book/list";
    }

    /**
     * 按出版社查询
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String findByPress(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        /*
         * 1. 得到pc：如果页面传递，使用页面的，如果没传，pc=1
         */
        int pc = getPc(req);
        /*
         * 2. 得到url：...
         */
        String url = getUrl(req);
        /*
         * 3. 获取查询条件，本方法就是cid，即分类的id
         */
        String press = req.getParameter("press");
        /*
         * 4. 使用pc和cid调用service#findByCategory得到PageBean
         */
        PageBean<OldBook> pb = bookService.findByPress(press, pc);
        /*
         * 5. 给PageBean设置url，保存PageBean，转发到/jsps/book/list.jsp
         */
        pb.setUrl(url);
        req.setAttribute("pb", pb);
        return "/jsps/book/list.jsp";
    }

    /**
     * 按图名查
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("findByBname")
    public String findByBname(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        /*
         * 1. 得到pc：如果页面传递，使用页面的，如果没传，pc=1
         */
        int pc = getPc(req);
        /*
         * 2. 得到url：...
         */
        String url = getUrl(req);
        /*
         * 3. 获取查询条件，本方法就是cid，即分类的id
         */
        String bname = req.getParameter("bname");
        /*
         * 4. 使用pc和cid调用service#findByCategory得到PageBean
         */
        bname="%"+bname+"%";
        PageBean<OldBook> pb = bookService.findByBname(bname, pc);
        /*
         * 5. 给PageBean设置url，保存PageBean，转发到/jsps/book/list.jsp
         */
        pb.setUrl(url);
        req.setAttribute("pb", pb);
        return "jsps/book/list";
    }

    /**
     * 多条件组合查询
     * @param req
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String findByCombination(HttpServletRequest req, HttpServletResponse resp,OldBook book)
            throws ServletException, IOException {
        /*
         * 1. 得到pc：如果页面传递，使用页面的，如果没传，pc=1
         */
        int pc = getPc(req);
        /*
         * 2. 得到url：...
         */
        String url = getUrl(req);
        /*
         * 3. 获取查询条件，本方法就是cid，即分类的id
         */
        //    Book criteria = CommonUtils.toBean(req.getParameterMap(), Book.class);
        /*
         * 4. 使用pc和cid调用service#findByCategory得到PageBean
         */
        PageBean<OldBook> pb = bookService.findByCombination(book, pc);
        /*
         * 5. 给PageBean设置url，保存PageBean，转发到/jsps/book/list.jsp
         */
        pb.setUrl(url);
        req.setAttribute("pb", pb);
        return "f:/jsps/book/list.jsp";
    }
}
