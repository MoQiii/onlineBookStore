package com.syj.olb.admin.dao;

import com.syj.olb.admin.pojo.Admin;

public interface AdminDao {
    public Admin findByName(String name);
}
