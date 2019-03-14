package com.syj.olb.category.service;

import com.syj.olb.category.pojo.Category;
import org.apache.ibatis.annotations.Param;


import java.util.List;
public interface CategoryService {
    public List<Category> findAll();
    public  List<Category> findParentCategory();
    public  List<Category> findChildCategoryByPC(String cname);
    public Category findCategoryByCname(@Param("Cname") String Cname);
}
