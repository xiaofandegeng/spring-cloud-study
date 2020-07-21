package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

    //添加服务端口号
    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")

    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("******插入结果：" + result);

        if (result > 0) {
            return new CommonResult(200,"插入数据库成功，serverPort：" + serverPort, result);
        }else {
            return new CommonResult(404,"插入数据库失败，serverPort：" + serverPort, null);
        }
    }

    @GetMapping(value = "/payment/getPaymentById/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment result = paymentService.getPaymentById(id);
        log.info("******插入结果：" + result);

        if (result != null){
            return new CommonResult(200,"根据id查询成功，serverPort：" + serverPort, result);
        }else {
            return new CommonResult(404,"没有查询到记录，查询id：" + id +"，serverPort：" + serverPort, null);
        }
    }

    @GetMapping(value = "/payment/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String s:services) {
            log.info(s);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PROVIDER-SERVICE");
        for (ServiceInstance s: instances) {
            log.info(s.getInstanceId() + "\t" + s.getServiceId() + "\t" + s.getHost() + "\t" + s.getPort() + "\t" + s.getUri());
        }
        return this.discoveryClient;

    }

}
