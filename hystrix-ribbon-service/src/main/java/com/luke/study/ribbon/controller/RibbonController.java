package com.luke.study.ribbon.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName: RibbonController
 * @Description:
 * @Author: Luke Fu
 * @Date: 2020/09/05 15:01
 **/
@Slf4j
@RestController
@RequestMapping("ribbon")
public class RibbonController {

    @Value("${service-url.eureka-client}")
    private String eurekaServer;
    @Autowired
    private RestTemplate restTemplate;

    private static Integer total=0;

    @GetMapping("test")
    @HystrixCommand(fallbackMethod = "getDefaultRibbonData",
            commandKey = "getUserCommand",
            groupKey = "getUserGroup",
            threadPoolKey = "getUserThreadPool")
    public String getRibbonData(String data){
        log.info("收到请求："+data);
        total++;
        return "第"+total+"次请求。"+restTemplate.getForObject(eurekaServer+"/api/test?name="+data,String.class);
    }

    private String getDefaultRibbonData(String data){
        return "错误";
    }
}
