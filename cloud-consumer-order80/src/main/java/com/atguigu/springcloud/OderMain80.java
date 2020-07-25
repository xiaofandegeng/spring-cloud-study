package com.atguigu.springcloud;

import com.atguigu.irule.MyRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "CLOUD-PROVIDER-SERVICE", configuration = MyRule.class)
public class OderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OderMain80.class, args);
    }
}
