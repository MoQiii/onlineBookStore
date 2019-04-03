package com.syj.olb.login.pojo;

import com.syj.olb.common.BaseModel;

public class OlbUser extends BaseModel {
    private String userName;
    private String passWord;

    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
