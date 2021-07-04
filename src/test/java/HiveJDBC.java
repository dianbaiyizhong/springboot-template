
import org.apache.hive.jdbc.HiveStatement;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;
public class HiveJDBC {
    private static String driverName = "org.apache.hive.jdbc.HiveDriver";
    private static String url = "jdbc:hive2://192.168.68.220:10000/default";
    private static String user = "root";
    private static String password = "";

    private static Connection conn = null;
    private static HiveStatement stmt = null;
    private static ResultSet rs = null;
    // 加载驱动、创建连接
    @Before
    public void init() throws Exception {
        Class.forName(driverName);
        conn = DriverManager.getConnection(url,user,password);
        stmt = (HiveStatement) conn.createStatement();
    }

    // 创建数据库
    @Test
    public void createDatabase() throws Exception {
        String sql = "create database hive_jdbc_test";
        System.out.println("Running: " + sql);
        stmt.execute(sql);
    }
    // 删除数据库表
    @Test
    public void dropTable() throws Exception {
        String sql = "drop table if exists emp";
        System.out.println("Running: " + sql);
        stmt.execute(sql);
    }

    // 加载数据
    @Test
    public void loadData() throws Exception {
        String filePath = "/usr/local/bigdata/data/emp.txt";
        String sql = "load data local inpath '" + filePath + "'  overwrite  into table emp";
        System.out.println("Running: " + sql);
        stmt.execute(sql);
    }
    // 创建表
    @Test
    public void createTable() throws Exception {
        String sql = "create table emp(\n" +
                "empno int,\n" +
                "ename string,\n" +
                "job string,\n" +
                "mgr int,\n" +
                "hiredate string,\n" +
                "sal double,\n" +
                "comm double,\n" +
                "deptno int\n" +
                ")\n" +
                "row format delimited fields terminated by ','";
        System.out.println("Running: " + sql);
        stmt.execute(sql);
    }

    // 统计查询（会运行mapreduce作业）
    @Test
    public void countData() throws Exception {
        String sql = "select count(1) from emp";
        System.out.println("Running: " + sql);
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
            System.out.println(rs.getInt(1) );
        }
    }


    // 释放资源
    @After
    public void destroy() throws Exception {
        if ( rs != null) {
            rs.close();
        }
        if (stmt != null) {
            stmt.close();
        }
        if (conn != null) {
            conn.close();
        }
    }


}
