//package com.example.blackoutsimulation.config;
//
//import com.example.blackoutsimulation.dao.BetTicketsDao;
//import com.example.blackoutsimulation.job.BlackOutSimulationJob;
//import com.example.blackoutsimulation.service.BetService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class QuartzConfig {
//    private Logger log = LoggerFactory.getLogger(this.getClass());
//
//    @Autowired
//    private BetTicketsDao betTicketsDao;
//
//    @Autowired
//    private BetService betService;
//
//    @Value("${common.corn}")
//    private String corn;
//
//    @Value("${common.beturl}")
//    private String beturl;
//
//    @Value("${common.bethistory}")
//    private String bethistory;
//
//    @Value("${common.cookies}")
//    private String cookies;
//
//    @Bean
//    public void WeatherDataJob() {
//        try {
//            QuartzManager.addJob("停电模拟", "blackOut_Group", "停电模拟任务触发器", "blackOut_GROUP", BlackOutSimulationJob.class, corn,bethistory,beturl,cookies,betTicketsDao,betService,log);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
