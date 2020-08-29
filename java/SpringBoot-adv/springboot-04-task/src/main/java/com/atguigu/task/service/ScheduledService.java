package com.atguigu.task.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledService {

    /**
     * second（秒）, minute（分）, hour（时）, day of month（日）, month（月）, day of week（周几）.  e.g.
     * "0 * * * * MON-FRI" 周一到周五的每一分钟（0秒，即整分钟的时间点）
     * 【0 0/5 14,18 * * ?】  每天14点和18点，每隔5分钟执行一次
     * 【0 15 10 ? * 1-6】    每个月的周五至周六10:15执行一次
     * 【0 0 2 ? * 6L】       每个月的最后一个周六凌晨2点执行一次
     * 【0 0 2 LW * ?】       每个月的最后一个工作日凌晨2点执行一次
     * 【0 0 2-4 ? * 1#1】    每个月的第一个周一凌晨2点到4点期间，每个整点执行一次
     */
//    @Scheduled(cron = "0 * * * * MON-SAT")
//    @Scheduled(cron = "0,1,2,3,4 * * * * MON-SAT")
//    @Scheduled(cron = "0-4 * * * * MON-SAT")
    @Scheduled(cron = "0/4 * * * * MON-SAT")    // 每4秒执行一次，但并不是一定从0秒开始，比如，项目启动时若是第25秒，则会从第28秒开始执行
    public void hello() {
        System.out.println("hello...");
    }
}
