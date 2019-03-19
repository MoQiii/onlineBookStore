package com.syj.olb.category.service;

import com.syj.olb.category.pojo.Category;
import org.apache.ibatis.annotations.Param;


import java.util.List;
public interface CategoryService {
    public List<Category> findAll();
    public  List<Category> findParentCategory();
    public  List<Category> findChildCategoryByPC(String cname);
    public Category findCategoryByCname( String Cname);
    public void add(Category category);
    /**
     * 删除分类
     * @param cid
     */
    public void delete(String cid);
    /**
     * 修改分类
     * 即可修改一级分类，也可修改二级分类
     * @param category
     */
    public void edit(Category category);
    /**
     * 查询指定父分类下子分类的个数
     * @param pid
     * @return
     */
    public int findChildrenCountByParent(String pid);
    /**
     * 加载分类
     * 即可加载一级分类，也可加载二级分类
     * @param cid
     * @return
     */
    public Category load(String cid);
    /**
     * 获取所有父分类，但不带子分类的！
     * @return
     */
    public List<Category> findParents();
}
