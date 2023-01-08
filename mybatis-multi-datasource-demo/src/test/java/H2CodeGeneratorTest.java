import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;

public class H2CodeGeneratorTest {
    /**
     * 数据源配置
     */


    private static final DataSourceConfig.Builder DATA_SOURCE_CONFIG = new DataSourceConfig
            .Builder("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE;CASE_INSENSITIVE_IDENTIFIERS=TRUE;MODE=MYSQL", "root", "");


    /**
     * 执行初始化数据库脚本
     */
    @Before
    public void before() throws SQLException {
        Connection conn = DATA_SOURCE_CONFIG.build().getConn();
        InputStream inputStream = H2CodeGeneratorTest.class.getResourceAsStream("/db/schema-h2.sql");
        ScriptRunner scriptRunner = new ScriptRunner(conn);
        scriptRunner.setAutoCommit(true);
        scriptRunner.runScript(new InputStreamReader(inputStream));
        conn.close();
    }


    @Test
    public void generate() {

        String projectPath = System.getProperty("user.dir") + "/" + "/src/main/java";
        FastAutoGenerator.create(DATA_SOURCE_CONFIG)
                .globalConfig(builder -> {
                    builder.author("nntk") // 设置作者
                            .outputDir(projectPath);
                })
                .packageConfig(builder -> {
                    builder.parent("com.zm.wsc") // 设置父包名
                            .moduleName("ds1.generate")
                            .controller("")
                    ;
                })
                .strategyConfig(builder -> {
                    builder.addInclude("T_USER")
                            .mapperBuilder().enableFileOverride()
                            .entityBuilder().enableFileOverride()
                    ;
                }).templateConfig(builder -> {
                    builder.disable(TemplateType.CONTROLLER, TemplateType.SERVICE, TemplateType.SERVICE_IMPL)

                    ;
                }).execute();
    }


}
