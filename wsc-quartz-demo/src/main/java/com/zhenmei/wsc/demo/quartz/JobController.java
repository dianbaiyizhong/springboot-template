package com.zhenmei.wsc.demo.quartz;


import com.zhenmei.wsc.constant.RestCode;
import com.zhenmei.wsc.response.ResultBuilder;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class JobController {
    @Autowired
    private Scheduler scheduler;


    /**
     * 新增任务
     *
     * @param quartz
     * @return
     */
    @RequestMapping("/addschedule")
    public Object save(QuartzBean quartz) throws Exception {
        log.info("新增任务");
        if (quartz.getOldJobGroup() != null) {
            JobKey key = new JobKey(quartz.getOldJobName(), quartz.getOldJobGroup());
            scheduler.deleteJob(key);
        }
        //获取.class
//        Class cls = Class.forName(quartz.getJobClassName());
//        cls.newInstance();
        //创建jobdetail


        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("taskInfoId", 111);
        JobDetail job = JobBuilder.newJob(DemoJob.class).withIdentity(quartz.getJobName(),
                        quartz.getJobGroup())
                //设置参数
                //.usingJobData("aa", "ceshi")
                //描述
                .setJobData(jobDataMap)
                .withDescription(quartz.getDescription())
                .build();
        // 使用cron表达式
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(quartz.getCronExpression());
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger" + quartz.getJobName(), quartz.getJobGroup())
                .startNow()
                .withSchedule(cronScheduleBuilder)
                .build();
        //交由Scheduler安排触发
        scheduler.scheduleJob(job, trigger);
        return ResultBuilder.success();
    }


    /**
     * 立即执行，但不影响定时器
     *
     * @param quartz
     * @return
     */
    @RequestMapping("/doschedule")
    public Object trigger(QuartzBean quartz) throws Exception {
        log.info("立即执行");
        JobKey key = new JobKey(quartz.getJobName(), quartz.getJobGroup());
        scheduler.triggerJob(key);
        return ResultBuilder.success();
    }


    /**
     * 暂停任务
     *
     * @param quartz
     * @return
     */
    @RequestMapping("/pauseschedule")
    public Object pause(QuartzBean quartz) throws Exception {
        log.info("停止任务");
        JobKey key = new JobKey(quartz.getJobName(), quartz.getJobGroup());
        scheduler.pauseJob(key);
        return ResultBuilder.success();
    }


    /**
     * 从暂停中恢复过来
     *
     * @param quartz
     * @return
     */
    @RequestMapping("/recoverschedule")
    public Object resume(QuartzBean quartz) throws Exception {
        log.info("恢复任务");
        JobKey key = new JobKey(quartz.getJobName(), quartz.getJobGroup());
        scheduler.resumeJob(key);
        return ResultBuilder.success();
    }


    /**
     * 删除任务
     *
     * @param quartz
     * @return
     */
    @RequestMapping("/deleteschedule")
    public Object remove(QuartzBean quartz) throws Exception {
        log.info("removeJob:" + JobKey.jobKey(quartz.getJobName()));
        TriggerKey triggerKey = TriggerKey.triggerKey(quartz.getJobName(), quartz.getJobGroup());
        // 停止触发器
        scheduler.pauseTrigger(triggerKey);
        // 移除触发器
        scheduler.unscheduleJob(triggerKey);
        // 删除任务
        scheduler.deleteJob(JobKey.jobKey(quartz.getJobName(), quartz.getJobGroup()));
        return ResultBuilder.success();
    }


}
