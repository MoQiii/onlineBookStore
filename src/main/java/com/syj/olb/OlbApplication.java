package com.syj.olb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;

@SpringBootApplication
@MapperScan("com.syj.olb")
public class OlbApplication {

    public static void main(String[] args) throws SQLException, ClassNotFoundException{
       /* String URL="jdbc:mysql://127.0.0.1:3306/goods?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8";
        String USER="root";
        String PASSWORD="root";
        //1.加载驱动程序
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2.获得数据库链接
        Connection conn= DriverManager.getConnection(URL, USER, PASSWORD);
        //3.通过数据库的连接操作数据库，实现增删改查（使用Statement类）
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("select * from t_user");
        //4.处理数据库的返回结果(使用ResultSet类)
        while(rs.next()){
            System.out.println(rs.getString("loginname")+" "
                    +rs.getString("loginpass"));
        }

        //关闭资源
        rs.close();
        st.close();
        conn.close();*/

        SpringApplication.run(OlbApplication.class, args);
    }

}

