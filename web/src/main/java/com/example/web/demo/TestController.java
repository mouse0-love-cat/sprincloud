package com.example.web.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

/**
 * @author Mr.Zhang
 */
@RestController
@RefreshScope
public class TestController {

    private final Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    private Registration registration;

    @Autowired
    private DiscoveryClient client;

    @Value("${nacos.name}")
    private String name;

    @GetMapping("/hello")
    public String index(){
        List<ServiceInstance> list = client.getInstances(registration.getServiceId());
        if (list != null && list.size() > 0) {
            for(ServiceInstance itm : list){
                logger.info("/hello,host:"+itm.getHost()+":"+itm.getPort()+",service_id:"+itm.getServiceId());
            }
        }
        return "Hello World "+name;
    }

}
