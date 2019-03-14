package com.syj.olb.user.pojo;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {
    @Select("select * from t_user where loginname=#{loginname} and loginpass=#{loginpass}")
    public User findByNP(@Param("loginname") String loginname, @Param("loginpass")String loginpass);
    @Select("select count(1) from t_user where email=#{email}")
    public int ajaxValidateEmail(@Param("email") String email);
    @Select("select count(1) from t_user where loginname=#{loginname}")
    public int ajaxValidateLoginname(@Param("loginname") String loginname);
    @Insert("insert into t_user values(#{user.uid},#{user.loginname},#{user.loginpass},#{user.email},#{user.status},#{user.activationCode})")
    public int add(@Param("user")User user);
    @Update("update t_user set loginpass=#{password} where uid=#{uid}")
    public void updatePassword(@Param("uid") String uid,@Param("password")String password);

    @Select("select count(*) from t_user where uid=#{uid} and loginpass=#{password}")
    public int findByUidAndPassword(@Param("uid")String uid, @Param("password")String password);
}
