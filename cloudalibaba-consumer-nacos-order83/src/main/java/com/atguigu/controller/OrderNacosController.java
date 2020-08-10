package com.atguigu.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderNacosController {

    //拿到RestTemplate
    @Autowired
    private RestTemplate restTemplate;

    // 配置nacos集群的url
    @Value("${service-url.nacos-user-service}")
    private String nacosUserService;

    @GetMapping("/order/payment/nacos/{id}")
    public String paymentInfo(@PathVariable("id") long id) {
        return restTemplate.getForObject(nacosUserService + "/payment/nacos/" + id, String.class);
    }
}
