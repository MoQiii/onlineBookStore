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

    @Override
    public void add(Category category) {
        categoryMapper.add(category);
    }

    @Override
    public List<Category> findChildren(String pid) {

        return categoryMapper.findChildren(pid);
    }

    /**
     * 删除分类
     *
     * @param cid
     */
    @Override
    public void delete(String cid) {
        categoryMapper.delete(cid);
    }

    /**
     * 修改分类
     * 即可修改一级分类，也可修改二级分类
     *
     * @param category
     */
    @Override
    public void edit(Category category) {
        categoryMapper.edit(category);
    }

    /**
     * 查询指定父分类下子分类的个数
     *
     * @param pid
     * @return
     */
    @Override
    public int findChildrenCountByParent(String pid) {
        return  categoryMapper.findChildrenCountByParent(pid);
    }

    /**
     * 加载分类
     * 即可加载一级分类，也可加载二级分类
     *
     * @param cid
     * @return
     */
    @Override
    public Category load(String cid) {

        return categoryMapper.load(cid);
    }

    /**
     * 获取所有父分类，但不带子分类的！
     *
     * @return
     */
    @Override
    public List<Category> findParents() {
        return categoryMapper.findParents();
    }
}
