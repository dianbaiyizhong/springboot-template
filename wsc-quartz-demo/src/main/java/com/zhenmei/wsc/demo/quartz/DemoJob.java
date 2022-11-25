package com.zhenmei.wsc.demo.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
@Slf4j
public class DemoJob implements Job {


    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {


        int id = context.getJobDetail().getJobDataMap().getInt("taskInfoId");
        log.info("___________执行一次:{}", id);


    }


}
