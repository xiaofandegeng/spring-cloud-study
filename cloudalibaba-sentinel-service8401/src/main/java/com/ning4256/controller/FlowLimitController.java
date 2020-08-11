package com.ning4256.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA(){
        return "---testA---";
    }

    @GetMapping("/testB")
    public String testB(){
        return "---testB---";
    }

}
