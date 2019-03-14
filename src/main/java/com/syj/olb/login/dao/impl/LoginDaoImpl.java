package com.syj.olb.login.dao.impl;

import com.syj.olb.login.dao.LoginDao;
import com.syj.olb.login.pojo.OlbUser;

import java.sql.*;
import java.util.List;

public class LoginDaoImpl implements LoginDao {

    @Override
    public List<OlbUser> selectAll() {

        return null;
    }

    @Override
    public boolean isExist(OlbUser user) {
        return false;
    }
    public void main(String[] args) {

    }
}
