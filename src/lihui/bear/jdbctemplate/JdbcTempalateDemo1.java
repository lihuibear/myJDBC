package lihui.bear.jdbctemplate;

import lihui.bear.util.jdbcUtils2;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTempalateDemo1 {
    public static void main(String[] args) {
         //1.导入
        //2.创建
        JdbcTemplate template = new JdbcTemplate(jdbcUtils2.getDataSource());
        //3.调用
        String sql = "update account set balance = 5000 where id = ?";
        int count = template.update(sql,4);
        System.out.println(count);

    }
}
