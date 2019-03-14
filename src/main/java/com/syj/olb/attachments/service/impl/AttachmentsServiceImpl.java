package com.syj.olb.attachments.service.impl;

import com.syj.olb.attachments.dao.AttachmentsDao;
import com.syj.olb.attachments.pojo.Attachments;
import com.syj.olb.attachments.service.AttachmentsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("AttachmentsServiceImpl")
public class AttachmentsServiceImpl implements AttachmentsService {

    @Resource(name="AttachmentsDaoImpl")
    AttachmentsDao attachmentsDao;
    @Override
    public void insertOldBook(Attachments attachments) {
        attachmentsDao.insertOldBook(attachments);
    }
}
