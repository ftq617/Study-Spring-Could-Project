package com.luke.style.client.ribbon;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: TestController
 * @Description: 测试服务提供方： controller
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
        log.info("Client B 收到消息："+name);
        total++;
        return "Client B 收到第"+total+"次请求 ："+name;
    }
}
