package com.zhenmei.wsc.demo;

import com.alibaba.druid.pool.DruidDataSource;
import com.zhenmei.wsc.demo.service.LockWaitServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@EnableScheduling
public class TestLockWait {

    @Autowired
    private LockWaitServiceImpl lockWaitService;


    @Autowired
    private DruidDataSource druidDs;

    @Scheduled(fixedRate = 10000)
    public void execute() throws SQLException {
//        for (int i = 0; i < 200; i++) {
//            lockWaitService.executeSome();
//        }
        Connection conn = druidDs.getConnection();
        PreparedStatement pstmt = conn.prepareStatement("select * from t_user_role");
        ResultSet resultSet = pstmt.executeQuery();

        while (resultSet.next()) {
            System.out.println("-------");
        }

        DruidDs.password = null;
        druidDs.setPassword(null);
    }


}
