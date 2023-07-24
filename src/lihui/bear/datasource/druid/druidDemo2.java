package lihui.bear.datasource.druid;

import com.alibaba.druid.util.JdbcUtils;
import lihui.bear.util.jdbcUtils2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class druidDemo2 {
    public static void main(String[] args) {
        PreparedStatement pstmt = null;
        Connection conn = null;
        try {
            conn = jdbcUtils2.getConnection();

            String sql = "insert into account values(null,?,?)";

            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, "王五");
            pstmt.setInt(2, 3000);

            int count = pstmt.executeUpdate();
            System.out.println();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtils2.close(pstmt, conn);
        }


    }
}
