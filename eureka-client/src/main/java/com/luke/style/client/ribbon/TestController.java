package com.luke.style.client.ribbon;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: TestController
 * @Description: 测试服务调用controller
 * @Author: Luke Fu
 * @Date: 2020/09/05 14:47
 **/
@Slf4j
@RestController
@RequestMapping("/api")
public class TestController {

    private static Integer total=0;

    @GetMapping("/test")
    public String getApiData(String name){
        log.info("Client A 收到消息："+name);
        total++;
        return "Client A 收到第"+total+"次请求 ："+name;
    }

    @GetMapping("/list")
    public List<String> getApiDataList(String name){
        log.info("Client list A 收到消息："+name);
        List<String> list=new ArrayList<>();
        list.add(name+1);
        list.add(name+2);
        list.add(name+3);
        return list;
    }
}
