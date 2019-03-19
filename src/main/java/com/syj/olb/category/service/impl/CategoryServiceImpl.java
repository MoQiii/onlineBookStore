package com.syj.olb.category.service.impl;

import com.syj.olb.category.dao.CategoryDao;
import com.syj.olb.category.pojo.Category;
import com.syj.olb.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service()
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

    @Override
    public void add(Category category) {
        categoryDao.add(category);
    }

    /**
     * 删除分类
     *
     * @param cid
     */
    @Override
    public void delete(String cid) {
        categoryDao.delete(cid);
    }

    /**
     * 修改分类
     * 即可修改一级分类，也可修改二级分类
     *
     * @param category
     */
    @Override
    public void edit(Category category) {
        categoryDao.edit(category);
    }

    /**
     * 查询指定父分类下子分类的个数
     *
     * @param pid
     * @return
     */
    @Override
    public int findChildrenCountByParent(String pid) {

        return categoryDao.findChildrenCountByParent(pid);
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

        return categoryDao.load(cid);
    }

    /**
     * 获取所有父分类，但不带子分类的！
     *
     * @return
     */
    @Override
    public List<Category> findParents() {
        return categoryDao.findParents();
    }


}
