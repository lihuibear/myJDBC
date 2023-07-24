package lihui.bear.jdbc;

import lihui.bear.domian.Emp;
import lihui.bear.util.jdbcUtils1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class jdbcDemo007 {
    public static void main(String[] args) {
        List<Emp> list = new jdbcDemo007().findALl2();
        System.out.println(list);
        System.out.println(list.size());

    }

    public List<Emp> findALl2() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Emp> list = null;
        try {

//            Class.forName("com.mysql.jdbc.Driver");
//
//            conn = DriverManager.getConnection("jdbc:mysql:///db1", "root", "root");
            conn = jdbcUtils1.getConnection();
            String sql = "select * from emp";
            stmt = conn.createStatement();

            rs = stmt.executeQuery(sql);
            list = new ArrayList<>();
            Emp emp = null;

            while (rs.next()) {
                int id = rs.getInt("id");
                String ename = rs.getString("ename");
                int job_id = rs.getInt("job_id");
                int mgr = rs.getInt("mgr");
                Date joindate = rs.getDate("joindate");
                double salary = rs.getDouble("salary");
                double bonus = rs.getDouble("bonus");
                int dept_id = rs.getInt("dept_id");

                emp = new Emp();

                emp.setId(id);
                emp.setEname(ename);
                emp.setJob_id(job_id);
                emp.setMgr(mgr);
                emp.setJoindate(joindate);
                emp.setSalary(salary);
                emp.setBonus(bonus);
                emp.setDept_id(dept_id);

                list.add(emp);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtils1.close(rs, stmt, conn);
        }
        return list;
    }
}

