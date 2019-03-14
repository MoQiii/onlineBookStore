package com.syj.olb.category.pojo;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface CategoryMapper {
    @Select("select * from t_category where pid is null order by orderBy")
    public List<Category> findAll();
    @Select("select * from t_category where pid=#{pid} order by orderBy")
    public List<Category> findByParent(String pid);
    @Select("select * from t_category where pid is null")
    public  List<Category> findParentCategory();
    @Select("select * from t_category where pid=(select cid from t_category where cname=#{pCname})")
    public  List<Category> findChildCategoryByPC(@Param("pCname") String pCname);
    @Select("select * from t_category where cname=#{Cname}")
    public Category findCategoryByCname(@Param("Cname") String Cname);
}
