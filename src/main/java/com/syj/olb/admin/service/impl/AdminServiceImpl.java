package com.syj.olb.admin.service.impl;

import com.syj.olb.admin.dao.AdminDao;
import com.syj.olb.admin.pojo.Admin;
import com.syj.olb.admin.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service("AdminServiceImpl")
public class AdminServiceImpl implements AdminService {
    @Resource(name="AdminDaoImpl")
    AdminDao adminDao;
    @Override
    public Admin findByName(String name) {
        return adminDao.findByName(name);
    }
}
