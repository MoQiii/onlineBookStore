package com.syj.olb.attachments.service;

import com.syj.olb.attachments.pojo.Attachments;
import org.springframework.web.multipart.MultipartFile;

public interface AttachmentsService {
    public void insertAttachments(Attachments attachments, MultipartFile file, String rPath);
}
