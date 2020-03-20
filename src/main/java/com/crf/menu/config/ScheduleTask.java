package com.crf.menu.config;

import com.crf.menu.service.CamasterService;
import com.crf.menu.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 自动定时任务,每一天凌晨1:00将redis中的category(全体分类)删除，换成mysql数据库中的数据，保证数据的一致性
 */
@Configuration
@Slf4j
@EnableScheduling
public class ScheduleTask {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private CamasterService camasterService;

    @Scheduled(cron = "0 0 1 * * ?")
    public void handleDelay(){
        //如果存在key,先把该key删除,再从mysql读取数据存入redis,不存在key，直接读取mysql数据存入redis
        if(redisUtil.hasKey("categoryVOList")){
            redisUtil.del("categoryVOList");
            redisUtil.set("categoryVOList",camasterService.getAllCategory());
            log.info("存在全体分类，mysql数据已更新到redis");
        }else{
            redisUtil.set("categoryVOList",camasterService.getAllCategory());
            log.info("不存在全体分类，mysql数据已更新到redis");
        }

    }
}