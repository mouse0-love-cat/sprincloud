package com.example.ribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @author Mr.Zhang
 */
public interface RibbonService {

    String ribbonHello();
}
