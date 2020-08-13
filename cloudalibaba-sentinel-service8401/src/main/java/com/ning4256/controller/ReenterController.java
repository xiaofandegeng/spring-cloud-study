package com.ning4256.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.ning4256.handler.CustomerBlockHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ReenterController {

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource", blockHandler = "deal_byResource",fallback = "deal_byResource")
    public CommonResult byResource() {
        return new CommonResult(200,"按资源名称限流测试OK",new Payment(2020L,"serial001"));
    }

    public CommonResult deal_byResource() {
        return new CommonResult(404,"按资源名称限流测试失败/(ㄒoㄒ)/~~",new Payment(2020L,"serial001"));
    }

    @GetMapping("/reenter/byUrl")
    @SentinelResource(value = "byUrl")
    public CommonResult byUrl() {
        return new CommonResult(200,"按url地址限流测试OK",new Payment(2020L,"serial001"));
    }

    @GetMapping("/get/defaultHanlder")
    @SentinelResource(value = "defaultHanlder", blockHandlerClass = CustomerBlockHandler.class, blockHandler = "default_Handler")
    public CommonResult defaultHanlder() {
        return new CommonResult(200,"按url地址限流测试OK",new Payment(2020L,"serial001"));
    }
}
