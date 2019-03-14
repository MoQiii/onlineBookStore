package com.syj.olb.category.service.impl;

import com.syj.olb.category.dao.CategoryDao;
import com.syj.olb.category.pojo.Category;
import com.syj.olb.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Resource(name="categoryDaoImpl")
    public CategoryDao categoryDao;
    @Override
    public List<Category> findAll() {

        return categoryDao.findAll();
    }

    @Override
    public List<Category> findParentCategory() {
        List<Category> parentCategory = categoryDao.findParentCategory();
        return parentCategory;
    }

    @Override
    public List<Category> findChildCategoryByPC(String cname) {
        return categoryDao.findChildCategoryByPC(cname);
    }

    @Override
    public Category findCategoryByCname(String Cname) {
        return categoryDao.findCategoryByCname(Cname);
    }
}
