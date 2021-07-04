import cn.hutool.core.thread.ThreadUtil;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledStatement;
import lombok.SneakyThrows;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.hive.jdbc.HiveDataSource;
import org.apache.hive.jdbc.HiveStatement;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class JdbcTemplateHiveTest {


    public static void main(String[] args) throws SQLException {
        DruidDataSource ds2 = new DruidDataSource();
        ds2.setDriverClassName("org.apache.hive.jdbc.HiveDriver");
        ds2.setUrl("jdbc:hive2://192.168.68.220:10000/default");
        ds2.setUsername("root");
        ds2.setPassword("");
        JdbcTemplate jdbcTemplate = new JdbcTemplate(ds2);

//        List list = jdbcTemplate.queryForList("select * from emp");
//
//        System.out.println(list);

        Connection conn = jdbcTemplate.getDataSource().getConnection();

        DruidPooledStatement stmt = (DruidPooledStatement) conn.createStatement();
        HiveStatement hiveStatement = (HiveStatement) stmt.getStatement();

        ThreadUtil.execAsync(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {

                while (true) {

                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(hiveStatement.getQueryLog());

                }
            }
        });
        hiveStatement.executeQuery("select count(1) from emp");




    }
}
