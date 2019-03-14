package com.syj.olb.user.dao.impl;

import com.syj.olb.user.dao.UserDao;
import com.syj.olb.user.pojo.User;
import com.syj.olb.user.pojo.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository("userDaoImpl")
public class UserDaoImpl implements UserDao {

    @Autowired
    public UserMapper userMapper;
    /**
     * 按uid和password查询
     *
     * @param uid
     * @param password
     * @return
     * @throws SQLException
     */
    @Override
    public boolean findByUidAndPassword(String uid, String password)  {
        int byUidAndPassword = userMapper.findByUidAndPassword(uid, password);
        return byUidAndPassword > 0 ? true : false;
    }

    /**
     * 修改密码
     *
     * @param uid
     * @param password
     * @throws SQLException
     */
    @Override
    public void updatePassword(String uid, String password)  {
        userMapper.updatePassword(uid,password);
    }

    /**
     * 按用户名和密码查询
     *
     * @param loginname
     * @param loginpass
     * @return
     * @throws SQLException
     */
    @Override
    public User findByLoginnameAndLoginpass(String loginname, String loginpass){
        User user = userMapper.findByNP(loginname, loginpass);
        return user;
    }

    /**
     * 通过激活码查询用户
     *
     * @param code
     * @return
     * @throws SQLException
     */
    @Override
    public User findByCode(String code)  {
        return null;
    }

    /**
     * 修改用户状态
     *
     * @param uid
     * @param status
     * @throws SQLException
     */
    @Override
    public void updateStatus(String uid, boolean status)  {

    }

    /**
     * 校验用户名是否注册
     *
     * @param loginname
     * @return
     * @throws SQLException
     */
    @Override
    public boolean ajaxValidateLoginname(String loginname)  {
        int count = userMapper.ajaxValidateLoginname(loginname);
        boolean b=false;
        if(count==0){
            b=true;
        }
        return b;
    }

    /**
     * 校验Email是否注册
     *
     * @param email
     * @return
     * @throws SQLException
     */
    @Override
    public boolean ajaxValidateEmail(String email)  {
        int count = userMapper.ajaxValidateEmail(email);
        boolean b=false;
        if(count==0){
            b=true;
        }
        return b;
    }

    /**
     * 添加用户
     *
     * @param user
     * @throws SQLException
     */
    @Override
    public void add(User user) {
        userMapper.add(user);
    }
}
