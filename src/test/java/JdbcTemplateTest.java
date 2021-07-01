import com.alibaba.druid.pool.DruidDataSource;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCountCallbackHandler;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.List;
import java.util.Map;

public class JdbcTemplateTest {

    public static void main(String[] args) {
        DruidDataSource ds2 = new DruidDataSource();
        ds2.setUrl("jdbc:mysql://150.158.186.188/mamba_view?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT&useTimezone=true");
        ds2.setUsername("root");
        ds2.setPassword("Mm5590026");
        JdbcTemplate jdbcTemplate = new JdbcTemplate(ds2);


        jdbcTemplate.execute("use mamba_view");

        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet("select * from t_device_info limit 0");


        System.out.println(ArrayUtils.toString(sqlRowSet.getMetaData().getColumnNames()));
//
        while (sqlRowSet.next()) {
            System.out.println(sqlRowSet.getString(1));
        }


    }
}
