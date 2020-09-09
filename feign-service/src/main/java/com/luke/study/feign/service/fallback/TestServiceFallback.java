package com.luke.study.feign.service.fallback;

import com.luke.study.feign.service.TestService;
import org.springframework.stereotype.Component;

/**
 * @ClassName: TestServiceFallback
 * @Description:
 * @Author: Luke Fu
 * @Date: 2020/09/09 11:16
 **/
@Component
public class TestServiceFallback implements TestService {
    public String getData(String id) {
        return id+" getData 出错了！";
    }

    public String getOther(String id) {
        return id+" getOther 出错了！";
    }
}
