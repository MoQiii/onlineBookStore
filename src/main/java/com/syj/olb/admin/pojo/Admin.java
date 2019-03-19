package com.syj.olb.admin.pojo;

import lombok.Data;

@Data
public class Admin {
    private String adminId;//主键
    private String adminname;//管理员的登录名
    private String adminpwd;//管理员的登录密码
}
