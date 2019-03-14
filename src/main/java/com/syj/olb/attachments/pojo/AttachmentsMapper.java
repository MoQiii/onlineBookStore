package com.syj.olb.attachments.pojo;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface AttachmentsMapper {
    @Insert("insert into t_attachments(id,busi_id,busi_type,file_name,file_url,file_size,file_type,save_name,user_id) values(" +
            "#{att.id},#{att.busiId},#{att.busiType},#{att.fileName},#{att.fileUrl},#{att.fileSize},#{att.fileType},#{att.saveName},#{att.userId})")
    public void insertAtt(@Param("att") Attachments attachments);
}
