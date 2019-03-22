package com.syj.olb.attachments.service.impl;

import com.syj.olb.attachments.dao.AttachmentsDao;
import com.syj.olb.attachments.pojo.Attachments;
import com.syj.olb.attachments.service.AttachmentsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;

@Service("AttachmentsServiceImpl")
@PropertySource("config/attachments.properties")
public class AttachmentsServiceImpl implements AttachmentsService {
    @Value("${attachment.filePath}")
    private String path;
    @Resource(name="AttachmentsDaoImpl")
    AttachmentsDao attachmentsDao;
    @Override
    public void insertAttachments(Attachments attachments, MultipartFile file,String rPath) {

       // String rPath=path+savePath;
        byte[] aByte=null;
        try(InputStream inputStream = file.getInputStream()) {
            aByte = new byte[inputStream.available()];
            inputStream.read(aByte);

            //写入磁盘
            File mkdir = new File(rPath);
            if (!mkdir.exists()) {
                mkdir.mkdirs();
            }
            //  rPath+="/"+originalFilename;
            rPath += "/" + attachments.getSaveName();
            try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(rPath))) {
                bos.write(aByte);
                bos.flush();
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        attachmentsDao.insertOldBook(attachments);
    }
}
