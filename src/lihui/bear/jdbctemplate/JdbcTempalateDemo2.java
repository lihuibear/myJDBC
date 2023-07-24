package lihui.bear.jdbctemplate;

import com.sun.javafx.collections.MappingChange;
import lihui.bear.domian.Emp;
import lihui.bear.util.jdbcUtils2;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 1. 修改1号数据的 salary 为 10000
 * 2. 添加一条记录
 * 3. 删除刚才添加的记录
 * 4. 查询id为1的记录，将其封装为Map集合
 * 5. 查询所有的记录，将其封装为List集合
 * 6. 查询所有记录，将其封装为Emp对象的List集合
 * 7. 查询总的记录数
 */
public class JdbcTempalateDemo2 {
    private JdbcTemplate template = new JdbcTemplate(jdbcUtils2.getDataSource());

    //1. 修改1号数据的 salary 为 10000
    @Test
    public void test1() {


        String sql = "update emp set salary = 10000 where id = ?";

        int count = template.update(sql, 1001);

        System.out.println(count);
    }

    //2. 添加一条记录
    @Test
    public void test2() {
        String sql = "insert into emp(id, ename, dept_id) values(?,?,?) ";

        int count = template.update(sql, 1015, "郭靖", 10);
        System.out.println(count);
    }

    //3. 删除刚才添加的记录
    @Test
    public void test3() {
        String sql = "delete from emp where id = ?";
        int count = template.update(sql, 1015);
        System.out.println(count);
    }

    /**
     * queryForMap(): 查询结果，将结果集封装为Map集合，将列名作为key，将值作为value，将这条记录封装为value
     * 注意：这个方法查询的结果集长度只能是1
     */
    // 4. 查询id为1的记录，将其封装为Map集合
    @Test
    public void test4() {
        String sql = "select * from emp where id = ?";

        Map<String, Object> map = template.queryForMap(sql, 1001);
        System.out.println(map);
    }

    //5. 查询所有的记录，将其封装为List集合
    @Test
    public void test5() {
        String sql = "select * from emp";
        List<Map<String, Object>> list = template.queryForList(sql);

        for (Map<String, Object> stringObjectMap : list) {
            System.out.println(stringObjectMap);
        }
    }

    //6. 查询所有记录，将其封装为Emp对象的List集合
    @Test
    public void test6_1() {
        String sql = "select * from emp";

        List<Emp> list = template.query(sql, new RowMapper<Emp>() {
            @Override
            public Emp mapRow(ResultSet rs, int i) throws SQLException {
                Emp emp = new Emp();
                int id = rs.getInt("id");
                String ename = rs.getString("ename");
                int job_id = rs.getInt("job_id");
                int mgr = rs.getInt("mgr");
                Date joindate = rs.getDate("joindate");
                double salary = rs.getDouble("salary");
                double bonus = rs.getDouble("bonus");
                int dept_id = rs.getInt("dept_id");

                emp.setId(id);
                emp.setEname(ename);
                emp.setJob_id(job_id);
                emp.setMgr(mgr);
                emp.setJoindate(joindate);
                emp.setSalary(salary);
                emp.setBonus(bonus);
                emp.setDept_id(dept_id);

                return emp;
            }
        });
        for (Emp emp : list) {
            System.out.println(emp);
        }
    }

    @Test
    public void test6_2() {
        String sql = "select * from emp";
        List<Emp> list = template.query(sql, new BeanPropertyRowMapper<Emp>(Emp.class));

        for(Emp emp : list){
            System.out.println(emp);
        }
    }

    //7. 查询总的记录数
    @Test
    public void test7() {
        String sql = "select count(id) from emp";
        Long total = template.queryForObject(sql, long.class);
        System.out.println(total);
    }
}
