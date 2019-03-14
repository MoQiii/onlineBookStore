package com.syj.olb.user.service;

import com.syj.olb.user.pojo.User;
import org.omg.CORBA.UserException;

import java.sql.SQLException;

public interface UserService {
    public void updatePassword(String uid, String newPass, String oldPass) throws UserException;
    /**
     * 登录功能
     * @param user
     * @return
     */
    public User login(User user) ;
    /**
     * 激活功能
     * @param code
     * @throws UserException
     */
    public void activatioin(String code) throws UserException;
    /**
     * 用户名注册校验
     * @param loginname
     * @return
     */
    public boolean ajaxValidateLoginname(String loginname);
    /**
     * Email校验
     * @param email
     * @return
     */
    public boolean ajaxValidateEmail(String email);
    /**
     * 注册功能
     * @param user
     */
    public void regist(User user);

}
