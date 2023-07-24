package lihui.bear.jdbc;

import lihui.bear.util.jdbcUtils1;

import java.sql.*;

import java.util.Scanner;

/**
 * 1. 通过键盘录入用户名和密码
 * 2. 判断用户是否登录成功
 */
public class jdbcDemo009 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("用户名");
        String username  = sc. nextLine();
        System.out.println("密码");
        String password  = sc. nextLine();

        boolean flag = new jdbcDemo009().login2(username, password);

        if(flag){
            System.out.println("成功");
        }else{
            System.out.println("失败 ");
        }
    }
    /**
     * 登录方法
     */
    public boolean login2(String username, String password) {
        if (username == null || password == null) {
            return false;
        }
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = jdbcUtils1.getConnection();

            String sql = "select * from user where username = ? and password = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,username);
            pstmt.setString(2,password);
            rs = pstmt.executeQuery();

            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            {
                jdbcUtils1.close(rs, pstmt, conn);
            }
        }
        return false;
    }
}
