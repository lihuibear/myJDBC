package lihui.bear.jdbc;

import lihui.bear.util.jdbcUtils1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * 1. 通过键盘录入用户名和密码
 * 2. 判断用户是否登录成功
 */
public class jdbcDemo008 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("用户名");
        String username  = sc. nextLine();
        System.out.println("密码");
        String password  = sc. nextLine();

        boolean flag = new jdbcDemo008().login(username, password);

        if(flag){
            System.out.println("成功");
        }else{
            System.out.println("shibai");
        }
    }
    /**
     * 登录方法
     */
    public boolean login(String username, String password) {
        if (username == null || password == null) {
            return false;
        }
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = jdbcUtils1.getConnection();

            String sql = "select * from user where username =  '"+username+"'and password = '"+password+"'";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
//            if(rs.next()){
//                return true;
//            }else {
//                return false;
//            }
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            {
                jdbcUtils1.close(rs, stmt, conn);
            }
        }
        return false;
    }
}
