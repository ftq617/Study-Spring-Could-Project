package com.luke.study.ribbon.controller;

import lombok.extern.slf4j.Slf4j;
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



    @GetMapping("test")
    public String getRibbonData(String data){
        log.info("收到请求："+data);
        return restTemplate.getForObject(eurekaServer+"/api/test?name="+data,String.class);
    }

    @GetMapping("get")
    public String getData(String id){
        log.info("get 收到请求："+id);
        return restTemplate.getForObject(eurekaServer+"/data/get?id="+id,String.class);
    }

    @GetMapping("post")
    public String postData(String id){
        log.info("post 收到请求："+id);
        return restTemplate.postForObject(eurekaServer+"/data/other?id="+id,"",String.class);
    }
}
