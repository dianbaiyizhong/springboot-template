import org.apache.hive.jdbc.HiveStatement;

import java.io.IOException;
import java.sql.*;

public class HiveExecutor {

    private static String driverName = "org.apache.hive.jdbc.HiveDriver";
    private static String url = "jdbc:hive2://192.168.68.220:10000/default";
    private static String user = "root";
    private static String password = "root";

    private static Connection conn = null;
    private static HiveStatement stmt = null;
    private static ResultSet rs = null;

    static class GetLogThread extends Thread {

        public void run() { //真生的输出运行进度的thread
            if (stmt == null) {
                return;
            }
            HiveStatement hiveStatement = (HiveStatement) stmt;
            try {
                while (!hiveStatement.isClosed() && ((HiveStatement) stmt).hasMoreLogs()) {
                    try {
                        for (String log : ((HiveStatement) stmt).getQueryLog(true, 100)) {
                            System.out.println("__________________" + log);
                        }
                        Thread.currentThread().sleep(500L);
                    } catch (SQLException e) { //防止while里面报错，导致一直退不出循环
                        e.printStackTrace();
                        return;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        return;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // ... 一些初始化
        // 加载数据连接
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(url, user, password);
            stmt = (HiveStatement) conn.createStatement();

            new GetLogThread().start();
            rs = stmt.executeQuery("select count(1) from emp");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }

                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}