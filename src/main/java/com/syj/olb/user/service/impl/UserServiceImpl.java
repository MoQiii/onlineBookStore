package com.syj.olb.user.service.impl;

import com.syj.olb.user.dao.UserDao;
import com.syj.olb.user.excepition.UserException;
import com.syj.olb.user.pojo.User;
import com.syj.olb.user.service.UserService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;



import java.util.UUID;


@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier("userDaoImpl")
    public UserDao userDao;

    @Override
    public void updatePassword(String uid, String newPass, String oldPass) {

            /*
             * 1. 校验老密码
             */
            boolean bool = userDao.findByUidAndPassword(uid, oldPass);
            if (!bool) {//如果老密码错误
                throw new UserException("旧密码错误！");
            }

            /*
             * 2. 修改密码
             */
            userDao.updatePassword(uid, newPass);

    }
    /**
     * 登录功能
     *
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        user = userDao.findByLoginnameAndLoginpass(user.getLoginname(), user.getLoginpass());
        return user;
    }

    /**
     * 激活功能
     *
     * @param code
     * @throws UserException
     */
    @Override
    public void activatioin(String code) throws UserException {

    }

    /**
     * 用户名注册校验
     *
     * @param loginname
     * @return
     */
    @Override
    public boolean ajaxValidateLoginname(String loginname) {
        boolean b = userDao.ajaxValidateLoginname(loginname);
        return b;
    }

    /**
     * Email校验
     *
     * @param email
     * @return
     */
    @Override
    public boolean ajaxValidateEmail(String email) {
        boolean b = userDao.ajaxValidateEmail(email);
        return b;
    }

    /**
     * 注册功能
     *
     * @param user
     */
    @Override
    public void regist(User user) {
        /*
         * 1. 数据的补齐
         */
        user.setUid(uuid());
        user.setStatus(true);
        user.setActivationCode(uuid() + uuid());
        /*
         * 2. 向数据库插入
         */
            userDao.add(user);
    }
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }
}
