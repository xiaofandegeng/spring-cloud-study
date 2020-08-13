package com.ning4256.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;

public class CustomerBlockHandler {
    public static CommonResult default_Handler(BlockException blockException) {
        return new CommonResult(4444, "只是系统默认的故障处理规则");
    }
}
