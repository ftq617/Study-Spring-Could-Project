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

    //@HystrixCommand中的常用参数
    //
    //fallbackMethod：指定服务降级处理方法；
    //ignoreExceptions：忽略某些异常，不发生服务降级；
    //commandKey：命令名称，用于区分不同的命令；
    //groupKey：分组名称，Hystrix会根据不同的分组来统计命令的告警及仪表盘信息；
    //threadPoolKey：线程池名称，用于划分线程池。
    //
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
