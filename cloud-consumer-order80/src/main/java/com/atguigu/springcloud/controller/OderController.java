package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Slf4j
@RestController
public class OderController {

    //public static final String PAYMENT_URL = "http://localhost:8001/";
    public static final String PAYMENT_URL = "http://CLOUD-PROVIDER-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }

    //使用restTemplate的postForEntity方法
    @GetMapping("/consumer/payment/postEntity/create")
    public CommonResult<Payment> postEntityCreate(Payment payment) {
        ResponseEntity<CommonResult> entity = restTemplate.postForEntity(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
        CommonResult result = entity.getBody();
        return result;
    }

    @GetMapping("/consumer/payment/getPaymentById/{id}")
    public  CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/getPaymentById/" + id, CommonResult.class);
    }

    //使用restTemplate的getForEntity方法
    @GetMapping("/consumer/payment/getEntity/{id}")
    public  CommonResult<Payment> getPaymentEntityById(@PathVariable("id") Long id) {
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/getPaymentById/" + id, CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()) {
            return entity.getBody();
        }else {
            return new CommonResult<>(404,"获取信息失败");
        }
    }
}
