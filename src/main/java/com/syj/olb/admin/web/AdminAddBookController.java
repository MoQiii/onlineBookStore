package com.syj.olb.admin.web;

import com.syj.olb.admin.pojo.Admin;
import com.syj.olb.attachments.pojo.Attachments;
import com.syj.olb.attachments.service.AttachmentsService;
import com.syj.olb.book.pojo.Book;
import com.syj.olb.book.service.BookService;
import com.syj.olb.category.pojo.Category;
import com.syj.olb.category.service.CategoryService;
import com.syj.olb.user.pojo.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller

@RequestMapping("/adminaddbook")
public class AdminAddBookController {
    /*@Value("${attachment.filePath}")
    private String filePath;
    @Value("${attachment.fileUrl}")
    private String fileUrl;*/
    @Resource(name="categoryServiceImpl")
    private CategoryService categoryService;
    @Resource(name="AttachmentsServiceImpl")
    private AttachmentsService attachmentsService;
    @Resource(name="bookServiceImpl")
    private BookService bookService;
    public String addBook(HttpServletRequest request, HttpServletResponse response,Book book,@RequestParam("image_w") MultipartFile image_w){
        //获取主键
     //   Book book = new Book();
        UUID uuid = UUID.randomUUID();
        book.setBid(uuid.toString().substring(0,12));
        Admin admin = new Admin();
        Object object = request.getSession().getAttribute("admin");
        if (object == null) {
            return "redirect:/user/loginToView";
        } else {
            admin = (Admin) object;
        }

     //   BeanUtils.populate(book, req.getParameterMap());
        String childCategory = request.getParameter("childCategory");

        Category categoryByCname = categoryService.findCategoryByCname(childCategory);
        book.setCategory(categoryByCname);

        Attachments attachments = new Attachments();
        attachments.setBusiId(uuid.toString().substring(0, 10));
        attachments.setBusiType("oldBookPic");

        String savePath = "/oldBookPic" + "/" + System.currentTimeMillis();
        /*String rPath="";
        String rFileUrl="";
        rPath=filePath+savePath;
        rFileUrl=fileUrl+savePath;*/

        //读取文件内容
        attachmentsService.insertAttachmentsAdmin(attachments, image_w,admin);
        book.setImage_b(attachments.getFileUrl());
        book.setImage_w(attachments.getFileUrl());
        bookService.add(book);
        return "redirect:/user/loginToView";
    }
}
