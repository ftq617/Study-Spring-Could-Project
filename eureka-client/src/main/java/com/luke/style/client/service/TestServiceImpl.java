package com.luke.style.client.service;

import com.luke.study.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: TestServiceImpl
 * @Description:
 * @Author: Luke Fu
 * @Date: 2020/09/09 11:20
 **/
@Slf4j
@RestController
public class TestServiceImpl implements TestService {

    @Override
    @PostMapping(GetData)
    public String getData(String id) {
        log.info("getData 收到消息：{}",id);
        return GetData+"收到消息："+id;
    }

    @Override
    @PostMapping(GetOther)
    public String getOther(String id) {
        log.info("GetOther 收到消息：{}",id);
        return GetOther+"收到消息："+id;
    }
}
