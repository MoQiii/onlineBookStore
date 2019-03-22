package com.syj.olb.attachments.web;

import com.syj.olb.attachments.pojo.Attachments;
import com.syj.olb.attachments.service.AttachmentsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@RequestMapping("/attachments")
@Controller
public class AttachmentsController {
    @Resource(name="AttachmentsServiceImpl")
    private AttachmentsService attachmentsService;
    @RequestMapping("/addAttachments")
    public void addAttachments(Attachments attachments){
  //      attachmentsService.insertOldBook(attachments);
    }
}
