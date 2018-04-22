package com.example.springbootjpa.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerTask {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private int count=0;

    @Scheduled(cron="*/6 * * * * ?")
    private void process(){
        logger.warn("this is scheduler task runing  "+(count++));
    }

}