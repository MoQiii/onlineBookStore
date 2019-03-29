package com.syj.olb.attachments.pojo;

import lombok.Data;

@Data
public class Attachments {
    private String id;
    private String busiId;
    private String busiType;
    private String fileName;
    private String fileType;
    private String fileUrl;
    private String filePath;
    private String saveName;
    private Long  fileSize;
    private String userId;
}
