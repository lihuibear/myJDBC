package lihui.bear.jdbc;
//测试
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class jdbcDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1", "root", "root");

        String sql = "update account set balance = 500 where id =1";
        Statement stmt = conn.createStatement();

        int count = stmt.executeUpdate(sql);
        System.out.println(count);

        stmt.close();
        conn.close();

    }
}


