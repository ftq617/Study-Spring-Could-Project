package com.luke.study.ribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.Future;

/**
 * @ClassName: RibbonService
 * @Description:
 * @Author: Luke Fu
 * @Date: 2020/09/08 16:43
 **/
@Slf4j
@Service
public class RibbonService {

    @Value("${service-url.eureka-client}")
    private String eurekaServer;
    @Autowired
    private RestTemplate restTemplate;


//    @CacheResult：开启缓存，默认所有参数作为缓存的key，cacheKeyMethod可以通过返回String类型的方法指定key；
//    @CacheKey：指定缓存的key，可以指定参数或指定参数中的属性值为缓存key，cacheKeyMethod还可以通过返回String类型的方法指定；
//    @CacheRemove：移除缓存，需要指定commandKey。

    //以上 必须和 @HystrixCommand注解一起使用



    @CacheResult  //缓存不能放在Controller，否则会失效，且只在同一个request情况下 ，缓存才有效果,
    @HystrixCommand(fallbackMethod = "getDefaultRibbonData", commandKey = "getCache")
    public String getCache(String id) {
        log.info("收到请求："+id);
        return restTemplate.getForObject(eurekaServer+"/api/test?name="+id,String.class);
    }

    private String getDefaultRibbonData(String data){
        return "错误";
    }


    @CacheRemove(commandKey = "getCache")
    @HystrixCommand
    public String removeCache(String id) {
        log.info("removeCache id:{}", id);
        return "SSD";
    }


//请求合并
//微服务系统中的服务间通信，需要通过远程调用来实现，随着调用次数越来越多，占用线程资源也会越来越多。Hystrix中提供了@HystrixCollapser用于合并请求，从而达到减少通信消耗及线程数量的效果。
//    @HystrixCollapser的常用属性
//
//    batchMethod：用于设置请求合并的方法；
//    collapserProperties：请求合并属性，用于控制实例属性，有很多；
//    timerDelayInMilliseconds：collapserProperties中的属性，用于控制每隔多少时间合并一次请求；

    @HystrixCollapser(batchMethod = "batch",collapserProperties = {
            @HystrixProperty(name = "timerDelayInMilliseconds", value = "1000")
    })
    public Future<String> getFuture(String id) {
        return new AsyncResult<String>(){
            @Override
            public String invoke() {
                return id;
            }
        };
    }



    @HystrixCommand
    public List<String> batch(List<String> ids) {
        log.info("getUserByIds:{}", ids);
        return restTemplate.getForObject(eurekaServer+"/api/list?name="+ids,List.class);
    }


}
