package com.syj.olb.admin.dao.impl;

import com.syj.olb.admin.dao.AdminDao;
import com.syj.olb.admin.pojo.Admin;
import com.syj.olb.admin.pojo.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository("AdminDaoImpl")
public class AdminDaoImpl implements AdminDao {
    @Autowired
    AdminMapper adminMapper;
    @Override
    public Admin findByName(String name) {
        return adminMapper.findByName(name);
    }
}
