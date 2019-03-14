package com.syj.olb.category.dao.impl;

import com.syj.olb.category.dao.CategoryDao;
import com.syj.olb.category.pojo.Category;
import com.syj.olb.category.pojo.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class CategoryDaoImpl implements CategoryDao {
    @Autowired
    public CategoryMapper categoryMapper;
    @Override
    public List<Category> findAll(){
        List<Category> parents = categoryMapper.findAll();
        /*
         * 2. 循环遍历所有的一级分类，为每个一级分类加载它的二级分类
         */
        for(Category parent : parents) {
            // 查询出当前父分类的所有子分类
            List<Category> children = categoryMapper.findByParent(parent.getCid());
            // 设置给父分类
            parent.setChildren(children);
        }
        return parents;
    }

    @Override
    public List<Category> findParentCategory() {
        List<Category> parentCategory = categoryMapper.findParentCategory();
        return parentCategory;
    }

    @Override
    public List<Category> findChildCategoryByPC(String cname) {
        List<Category> childCategoryByPC = categoryMapper.findChildCategoryByPC(cname);
        return childCategoryByPC;
    }

    @Override
    public Category findCategoryByCname(String Cname) {
        return categoryMapper.findCategoryByCname(Cname);
    }
}
