package com.luke.study.service.fallback;

import com.luke.study.service.TestService;
import org.springframework.stereotype.Component;

/**
 * @ClassName: TestServiceFallback
 * @Description:
 * @Author: Luke Fu
 * @Date: 2020/09/09 11:16
 **/
@Component
public class TestServiceFallback {
    public String getData(String id) {
        return id+" getData 出错了！";
    }

    public String getOther(String id) {
        return id+" getOther 出错了！";
    }
}
