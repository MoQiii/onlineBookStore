package com.syj.olb.category.pojo;

import org.apache.ibatis.annotations.*;

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
    @Insert("insert into t_category(cid,cname,pid,description)values(#{category.cid},#{category.cname},#{category.pid},#{category.description})")
    public void add(@Param("category")Category category);
    @Select("select * from t_category where cid=#{cid}")
    public Category load(@Param("cid")String cid);
    @Update("update t_category set description=#{category.description},cname=#{category.cname} where cid=#{category.cid}")
    public void edit(@Param("category") Category category);
    @Select("select count(*) from t_category where pid=#{pid}")
    public int findChildrenCountByParent(@Param("pid")String pid);
    @Delete("delete from t_category where cid=#{cid}")
    public int delete(@Param("cid")String cid);
    @Select("select * from t_category where pid is null order by orderBy")
    public List<Category> findParents();
}
