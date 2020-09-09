package com.luke.study.feign.controller;

import com.luke.study.feign.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: TestController
 * @Description:
 * @Author: Luke Fu
 * @Date: 2020/09/09 11:30
 **/
@Slf4j
@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/get")
    public String getData(String id){
        log.info("getData 收到消息：{}",id);
        System.out.println("TestService："+testService.getClass());

        return testService.getData(id);
    }

    @GetMapping("/other")
    public String getOther(String id){
        log.info("getOther 收到消息：{}",id);
        return testService.getOther(id);
    }

}
