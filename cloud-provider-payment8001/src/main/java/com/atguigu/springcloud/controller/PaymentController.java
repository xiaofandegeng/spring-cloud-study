package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @PostMapping(value = "/payment/create")

    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("******插入结果：" + result);

        if (result > 0) {
            return new CommonResult(200,"插入数据库成功", result);
        }else {
            return new CommonResult(404,"插入数据库失败", null);
        }
    }

    @GetMapping(value = "/payment/getPaymentById/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment result = paymentService.getPaymentById(id);
        log.info("******插入结果：" + result);

        if (result != null){
            return new CommonResult(200,"根据id查询成功", result);
        }else {
            return new CommonResult(404,"没有查询到记录，查询id：" + id, null);
        }
    }

}
