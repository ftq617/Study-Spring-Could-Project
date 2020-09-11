package com.luke.study.ribbon.controller;

import com.luke.study.ribbon.service.RibbonService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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
    @Autowired
    private RibbonService ribbonService;

    private static Integer total=0;

    //@HystrixCommand中的常用参数
    //
    //fallbackMethod：指定服务降级处理方法；
    //ignoreExceptions：忽略某些异常，不发生服务降级；
    //commandKey：命令名称，用于区分不同的命令，用来区分不同方法；
    //groupKey：分组名称，Hystrix会根据不同的分组来统计命令的告警及仪表盘信息；
    //threadPoolKey：线程池名称，用于划分线程池。
    //
    @GetMapping("test")
    @HystrixCommand(fallbackMethod = "getDefaultRibbonData",
            commandKey = "getRibbonData",
            groupKey = "getRibbonGroup",
            threadPoolKey = "getUserThreadPool")
    public String getRibbonData(String data){
        log.info("收到请求："+data);
        total++;
        return "第"+total+"次请求。"+restTemplate.getForObject(eurekaServer+"/api/test?name="+data,String.class);
    }

    private String getDefaultRibbonData(String data){
        return "错误";
    }


    @GetMapping("cache")
    public String cache(String id){
        ribbonService.getCache(id);
        log.info("1："+id);
        ribbonService.getCache(id);
        ribbonService.removeCache(id);
        log.info("2："+id);

        return ribbonService.getCache(id);
    }

    @GetMapping("batch")
    public String batch(String id) throws ExecutionException, InterruptedException {
        Future future1=ribbonService.getFuture(id+1);
        Future future2=ribbonService.getFuture(id+2);
        Future future3=ribbonService.getFuture(id+3);
        log.info("结果："); //分配结果是按合并后 ，ID的顺序分配
        log.info("1:"+future1.get());
        log.info("2:"+future2.get());
        log.info("3:"+future3.get());
        return future1.get()+" "+future2.get()+" "+future3.get();
    }

}
