package com.syj.olb.attachments.service;

import com.syj.olb.admin.pojo.Admin;
import com.syj.olb.attachments.pojo.Attachments;
import com.syj.olb.user.pojo.User;
import org.springframework.web.multipart.MultipartFile;

public interface AttachmentsService {
    public void insertAttachments(Attachments attachments, MultipartFile file,User user);
    public void insertAttachmentsAdmin(Attachments attachments, MultipartFile file, Admin admin);
}
