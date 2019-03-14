package com.syj.olb.user.dao;

import com.syj.olb.user.pojo.User;

import java.sql.SQLException;

public interface UserDao {
    /**
     * 按uid和password查询
     * @param uid
     * @param password
     * @return
     * @throws SQLException
     */
    public boolean findByUidAndPassword(String uid, String password) ;
    /**
     * 修改密码
     * @param uid
     * @param password
     * @throws SQLException
     */
    public void updatePassword(String uid, String password) ;
    /**
     * 按用户名和密码查询
     * @param loginname
     * @param loginpass
     * @return
     * @throws SQLException
     */
    public User findByLoginnameAndLoginpass(String loginname, String loginpass) ;
    /**
     * 通过激活码查询用户
     * @param code
     * @return
     * @throws SQLException
     */
    public User findByCode(String code) ;
    /**
     * 修改用户状态
     * @param uid
     * @param status
     * @throws SQLException
     */
    public void updateStatus(String uid, boolean status) ;
    /**
     * 校验用户名是否注册
     * @param loginname
     * @return
     * @throws SQLException
     */
    public boolean ajaxValidateLoginname(String loginname) ;
    /**
     * 校验Email是否注册
     * @param email
     * @return
     * @throws SQLException
     */
    public boolean ajaxValidateEmail(String email) ;
    /**
     * 添加用户
     * @param user
     * @throws SQLException
     */
    public void add(User user) ;

}
