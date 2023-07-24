package lihui.bear.datasource.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * c3p0的演示
 */
public class C3P0demo1 {
    public static void main(String[] args) throws SQLException {
        DataSource ds = new ComboPooledDataSource();

        for (int i = 0; i <= 10; i++) {
            Connection conn = ds.getConnection();
            System.out.println(conn);

        }
    }
}
