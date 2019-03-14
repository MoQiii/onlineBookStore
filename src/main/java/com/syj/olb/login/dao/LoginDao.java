package com.syj.olb.login.dao;

import com.syj.olb.login.pojo.OlbUser;

import java.sql.SQLException;
import java.util.List;

public interface LoginDao {
    public List<OlbUser> selectAll() throws SQLException, ClassNotFoundException;
    public  boolean  isExist(OlbUser user);
}
