package com.syj.olb.attachments.dao.impl;

import com.syj.olb.attachments.dao.AttachmentsDao;
import com.syj.olb.attachments.pojo.Attachments;
import com.syj.olb.attachments.pojo.AttachmentsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("AttachmentsDaoImpl")
public class AttachmentsDaoImpl implements AttachmentsDao {
    @Autowired
    AttachmentsMapper attachmentsMapper;
    @Override
    public void insertOldBook(Attachments attachments) {
        attachmentsMapper.insertAtt(attachments);
    }
}
