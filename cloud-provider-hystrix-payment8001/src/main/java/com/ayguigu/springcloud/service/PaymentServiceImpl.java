package com.ayguigu.springcloud.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + "paymentInfo_OK, id:" + id;
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        final int TIME_SLEEP = 3;
        try {
            TimeUnit.SECONDS.sleep(TIME_SLEEP);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + "paymentInfo_TimeOut, id:" + id + "耗时：" + TIME_SLEEP + "秒";
    }
}
