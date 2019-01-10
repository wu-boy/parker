package com.wu.parker.schedule.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author: wusq
 * @date: 2019/1/10
 */
@Component
public class MyTask {

    @Scheduled(cron="*/6 * * * * ?")
    private void execute(){
        System.out.println("每6秒执行一次");
    }

}
