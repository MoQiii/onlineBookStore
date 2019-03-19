package com.syj.olb.admin.pojo;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface AdminMapper {
    @Select("select * from t_admin where adminname=#{name}")
    public Admin findByName(@Param("name") String name);
}
