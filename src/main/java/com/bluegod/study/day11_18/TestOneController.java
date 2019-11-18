package com.bluegod.study.day11_18;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestOneController {

    @GetMapping("hello")
    public String getHello(){
        return "Hello word";
    }
}
