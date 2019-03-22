package com.syj.olb.admin.web;

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
import java.io.UnsupportedEncodingException;
import java.util.UUID;

@Controller
@PropertySource("config/attachments.properties")
@RequestMapping("/adminaddbook")
public class AdminAddBookController {
    @Value("${attachment.filePath}")
    private String path;
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
        User user = new User();
        Object object = request.getSession().getAttribute("sessionUser");
        if (object == null) {
            return "redirect:/user/loginToView";
        } else {
            user = (User) object;
        }

     //   BeanUtils.populate(book, req.getParameterMap());
        String childCategory = request.getParameter("childCategory");

        Category categoryByCname = categoryService.findCategoryByCname(childCategory);
        book.setCategory(categoryByCname);

        Attachments attachments = new Attachments();
        attachments.setBusiId(uuid.toString().substring(0, 10));
        attachments.setBusiType("oldBookPic");

        String originalFilename = image_w.getOriginalFilename();
        String savePath = "/oldBookPic" + "/" + System.currentTimeMillis();
        String rPath="";
        rPath=path+savePath;
        attachments.setFileUrl(rPath);

        int i = originalFilename.lastIndexOf(".");
        String substring = originalFilename.substring(0, i - 1);
        String fileType = originalFilename.substring(i);
        attachments.setFileType(fileType);
        attachments.setFileName(substring);
        attachments.setFileSize(image_w.getSize());
        attachments.setId(uuid.toString().substring(0, 10));
        attachments.setSaveName(UUID.randomUUID().toString());
        attachments.setUserId(user.getUid());
        book.setImage_b(savePath + "/" + attachments.getSaveName());
        book.setImage_w(savePath + "/" + attachments.getSaveName());
        //读取文件内容
        attachmentsService.insertAttachments(attachments, image_w, rPath);

        bookService.add(book);
        return "redirect:/user/loginToView";
    }
}
