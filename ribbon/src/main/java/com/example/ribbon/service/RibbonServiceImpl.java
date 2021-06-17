package com.example.ribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Mr.Zhang
 */
@Service
public class RibbonServiceImpl implements RibbonService{

    @Autowired
    private RestTemplate REST_TEMPLATE = new RestTemplate();

    @Override
    @HystrixCommand(fallbackMethod = "helloFallback")
    public String ribbonHello() {
        return REST_TEMPLATE.getForEntity("http://web-test/hello", String.class).getBody();
    }

    public String helloFallback() {
        return "error";
    }
}
