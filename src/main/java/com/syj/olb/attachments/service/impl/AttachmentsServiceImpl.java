package com.syj.olb.attachments.service.impl;

import com.syj.olb.admin.pojo.Admin;
import com.syj.olb.attachments.dao.AttachmentsDao;
import com.syj.olb.attachments.pojo.Attachments;
import com.syj.olb.attachments.service.AttachmentsService;
import com.syj.olb.user.pojo.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.util.UUID;

@Service("AttachmentsServiceImpl")
@ConfigurationProperties(prefix = "attachment")
@PropertySource("config/attachments.properties")
public class AttachmentsServiceImpl implements AttachmentsService {
    @Value("${attachment.filePath}")
    private String filePath;
    @Value("${attachment.fileUrl}")
    private String fileUrl;
    @Resource(name="AttachmentsDaoImpl")
    AttachmentsDao attachmentsDao;
    @Override
    public void insertAttachments(Attachments attachments, MultipartFile file,  User user) {

        // String rPath=path+savePath;
        UUID uuid = UUID.randomUUID();
        String savePath = "/oldBookPic"+"/"+ System.currentTimeMillis()+"/";
        String rPath="";
        String rFileUrl="";
        rPath=filePath+savePath;//F:/olbPic/oldBookPic+"/"+ System.currentTimeMillis()/;
        rFileUrl=fileUrl+savePath;//http:localhost:8082/olbPic/oldBookPic+"/"+ System.currentTimeMillis()/;

        String originalFilename = file.getOriginalFilename();
        attachments.setFileUrl(rFileUrl+originalFilename);
        attachments.setFilePath(rPath+originalFilename);
        int i = originalFilename.lastIndexOf(".");
        String substring = originalFilename.substring(0, i - 1);
        String fileType = originalFilename.substring(i);
        attachments.setFileType(fileType);
        attachments.setFileName(substring);
        attachments.setFileSize(file.getSize());
        attachments.setId(uuid.toString().substring(0,10));
        attachments.setSaveName(UUID.randomUUID().toString());
        attachments.setUserId(user.getUid());
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
      //      rPath += "/" + attachments.getSaveName();
            try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(attachments.getFilePath()))) {
                bos.write(aByte);
                bos.flush();
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        attachmentsDao.insertOldBook(attachments);
    }
    @Override
    public void insertAttachmentsAdmin(Attachments attachments, MultipartFile file,Admin admin) {

        // String rPath=path+savePath;
        UUID uuid = UUID.randomUUID();
        String savePath = "/oldBookPic"+"/"+ System.currentTimeMillis()+"/";
        String rPath="";
        String rFileUrl="";
        rPath=filePath+savePath;//F:/olbPic/oldBookPic+"/"+ System.currentTimeMillis()/;
        rFileUrl=fileUrl+savePath;//http:localhost:8082/pic/oldBookPic+"/"+ System.currentTimeMillis()/;
        attachments.setFileUrl(rFileUrl);
        attachments.setFilePath(rPath);
        String originalFilename = file.getOriginalFilename();
        int i = originalFilename.lastIndexOf(".");
        String substring = originalFilename.substring(0, i - 1);
        String fileType = originalFilename.substring(i);
        attachments.setFileType(fileType);
        attachments.setFileName(substring);
        attachments.setFileSize(file.getSize());
        attachments.setId(uuid.toString().substring(0,10));
        attachments.setSaveName(UUID.randomUUID().toString());
        attachments.setUserId(admin.getAdminId());
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
