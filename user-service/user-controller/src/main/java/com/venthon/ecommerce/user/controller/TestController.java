package com.venthon.ecommerce.user.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping(value = "/hello")
    public String hello() {
        return "Hello Sentinel";
    }

    // Slow endpoint to trigger the circuit breaker
    @GetMapping("/slow")
    @SentinelResource(value = "slowUser", fallback = "slowFallback", blockHandler = "slowBlocked")
    public String slow() throws InterruptedException {
        Thread.sleep(500);
        return "slow ok";
    }

    public String slowFallback(Throwable t) { return "fallback: " + t.getMessage(); }
    public String slowBlocked(BlockException ex) { return "circuit OPEN"; }


    public String helloBlocked() {
        return "Hello Blocked";
    }

    public String helloFallback(Throwable t) {
        return "Hello Fallback, Data from cache";
    }
}

