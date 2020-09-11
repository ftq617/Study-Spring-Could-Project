package com.luke.study.service;

import com.luke.study.service.fallback.TestServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "eureka-client",fallback = TestServiceFallback.class)
public interface TestService {

    String module="/data";
    String GetData=module+"/get";
    String GetOther=module+"/other";

    //支持restFul风格

    @GetMapping(GetData)  //get 请求必须要加 @RequestParam注解，否则会转换成 post请求
    String getData(@RequestParam("id") String id);

    @PostMapping(GetOther)  //基本对象要用@RequestParam，否则不能传值 ，封装对象要用 @RequestBody ,
    String getOther(@RequestParam("id") String id);
}
