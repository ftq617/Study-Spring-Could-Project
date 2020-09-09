package com.luke.study.feign.service;

import com.luke.study.feign.service.fallback.TestServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "eureka-client",fallback = TestServiceFallback.class)
public interface TestService {

    String module="/data";
    String GetData=module+"/get";
    String GetOther=module+"/other";

    @PostMapping(GetData)
    String getData(String id);

    @PostMapping(GetOther)
    String getOther(String id);
}
