package com.kevin.tabindicator.samples;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by dell on 2016/6/23.
 *
 * 问题：这里面的方法没有执行！！
 */
public class dboperation {
    public Connection con;
    public Statement stmt;

    dboperation() {
        Log.e("JDBC","Begin connect");
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();// 加载驱动程序
            String url="jdbc:mysql://127.0.0.1:3306/user_db";
            String name="root";
            String password="admin";
            con = DriverManager.getConnection(url, name, password);
            // com.mysql.jdbc.exceptions.jdbc4.CommunicationsException: Communications link failure
            Log.e("JDBC","connect database finish!!!");
            Log.e("JDBC","加载驱动完毕");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //stmt = con.createStatement();
        Log.e("JDBC","connect completed");

    }



    public boolean selectall_operation() {
        boolean flag = false;
        try {
            String sql = "select * from user";
            Statement stmt = con.createStatement();//创建Statement
            ResultSet rs = stmt.executeQuery(sql);

            //System.out.println("展示resultSet集合");
//            while(rs.next()){
//                System.out.println(rs.getInt(1)+"---"+rs.getString(2));
//            }
            if (!rs.wasNull()) {
                flag = true;
            } else {
                flag = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    //返回size
    public int size() {
        int size=0;
        try {
            String sql = "select * from user";
            Statement stmt = con.createStatement();//创建Statement
            ResultSet rs = stmt.executeQuery(sql);

            //System.out.println("展示resultSet集合");
//            while(rs.next()){
//                System.out.println(rs.getInt(1)+"---"+rs.getString(2));
//            }
            size=rs.getRow();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    //登陆验证用户信息
    public void login_getUser(String name, String password) {

    }

    //根据用户名和密码，取出用户信息
    public ArrayList<User> get_info(String name, String password) {
        ArrayList<User> list = null;
        ResultSet rs=null;
        try {
            list = new ArrayList<>();
            String sql = "select * from user where name=? and password=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1,name);
            st.setString(2,password);
            Log.e("SQL",sql);
            rs=st.executeQuery();
            Log.e("SQL","ohhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
            while(rs.next()){
                User u=new User(rs.getString(2),rs.getString(3),rs.getInt(4));
                list.add(u);
            }
            Log.e("GET_INFO","获取信息成功！");
            Log.e("GET_INFO",String.valueOf(list.size()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    //插入用户信息
    public boolean insert(String name, String password, int sex) {
        boolean flag = false;

        return flag;
    }
}
