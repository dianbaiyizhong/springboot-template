import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLLimit;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.*;
import com.alibaba.druid.sql.visitor.SQLASTVisitor;
import com.alibaba.druid.sql.visitor.SQLASTVisitorAdapter;
import com.alibaba.druid.util.JdbcConstants;
import org.junit.Test;

import java.util.List;

public class SqlParser {

    @Test
    public void replaceLimit() {
        String sql = "SELECT *\n" +
                "FROM t_order\n" +
                "WHERE id = 1\n" +
                "\tAND name = a\n" +
                "LIMIT 11 --fds" +
                "\n";

        List<SQLStatement> statementList = SQLUtils.parseStatements(sql, JdbcConstants.HIVE);
        SQLSelectStatement sqlSelectStatement = (SQLSelectStatement) statementList.get(0);
        SQLSelectQuery sqlSelectQuery = sqlSelectStatement.getSelect().getQuery();

        if (sqlSelectQuery instanceof SQLSelectQueryBlock) {
            SQLSelectQueryBlock sqlSelectQueryBlock = (SQLSelectQueryBlock) sqlSelectQuery;
            sqlSelectQueryBlock.setLimit(new SQLLimit(20));
        }

        System.out.println(sqlSelectQuery.toString());
    }

    @Test
    public void selectLimit() {
        String sql = "SELECT *\n" +
                "FROM t_order\n" +
                "WHERE id = 1\n" +
                "\tAND name = a\n" +
                "LIMIT 11 --fds" +
                "\n";

        String format = SQLUtils.formatHive(sql);
        System.out.println(format);

        List<SQLStatement> statementList = SQLUtils.parseStatements(sql, JdbcConstants.HIVE);

        SQLSelectStatement sqlSelectStatement = (SQLSelectStatement) statementList.get(0);
        SQLSelectQuery sqlSelectQuery = sqlSelectStatement.getSelect().getQuery();

        // 非union的查询语句
        if (sqlSelectQuery instanceof SQLSelectQueryBlock) {
            SQLSelectQueryBlock sqlSelectQueryBlock = (SQLSelectQueryBlock) sqlSelectQuery;
            SQLLimit limit = sqlSelectQueryBlock.getLimit();

            System.out.println(limit.getRowCount());
        }


    }
}
