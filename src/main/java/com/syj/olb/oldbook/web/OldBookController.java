package com.syj.olb.oldbook.web;

import com.syj.olb.attachments.pojo.Attachments;
import com.syj.olb.attachments.service.AttachmentsService;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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
import java.util.UUID;

@RequestMapping("/OldBook")
@Controller
@ConfigurationProperties(prefix = "attment")
@PropertySource("config/attachments.properties")
public class OldBookController {

    @Resource(name="oldBookServiceImpl")
    private OldBookService bookService;
    @Value("${attment.filePath}")
    private String path;
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
    @RequestMapping("/add")
    public String addOldBook(HttpServletRequest req, HttpServletResponse res,@RequestParam("file") MultipartFile file) throws InvocationTargetException, IllegalAccessException, IOException {
        //获取主键
        OldBook oldBook=new OldBook();
        UUID uuid = UUID.randomUUID();

        User user=new User();
        Object object = req.getSession().getAttribute("sessionUser");
        if(object==null){
            return "redirect:/user/loginToView";
        }
        else{
            user=(User)object;
        }

        BeanUtils.populate(oldBook,req.getParameterMap());
        String childCategory = req.getParameter("childCategory");

        Category categoryByCname = categoryService.findCategoryByCname(childCategory);
        oldBook.setCategory(categoryByCname);
        String rPath="";
        Attachments attachments = new Attachments();
        attachments.setBusiId(uuid.toString().substring(0,10));
        attachments.setBusiType("oldBookPic");

        String originalFilename = file.getOriginalFilename();
        String savePath = "/oldBookPic"+"/"+ System.currentTimeMillis();
        rPath=path+savePath;
        attachments.setFileUrl(rPath);

        int i = originalFilename.lastIndexOf(".");
        String substring = originalFilename.substring(0, i - 1);
        String fileType = originalFilename.substring(i);
        attachments.setFileType(fileType);
        attachments.setFileName(substring);
        attachments.setFileSize(file.getSize());
        attachments.setId(uuid.toString().substring(0,10));
        attachments.setSaveName(UUID.randomUUID().toString());
        attachments.setUserId(user.getUid());
        oldBook.setImage_b(savePath+"/"+attachments.getSaveName());
        oldBook.setImage_w(savePath+"/"+attachments.getSaveName());
        //读取文件内容
        attachmentsService.insertOldBook(attachments);
        byte[] aByte=null;
        try(InputStream inputStream = file.getInputStream()){
           aByte = new byte[inputStream.available()];
            inputStream.read(aByte);
        }
        //写入磁盘
        File mkdir=new File(rPath);
        if(!mkdir.exists()){
            mkdir.mkdirs();
        }
      //  rPath+="/"+originalFilename;
        rPath+="/"+attachments.getSaveName();
        try(BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(rPath))){
            bos.write(aByte);
            bos.flush();
        }

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
    public String load(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String bid = req.getParameter("bid");//获取链接的参数bid
        OldBook book = bookService.load(bid);//通过bid得到book对象
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
        /*
         * 4. 使用pc和cid调用service#findByCategory得到PageBean
         */
        PageBean<OldBook> pb = bookService.findByCategory(bookQuery);
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
