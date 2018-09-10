package com.example.microserviceconsumermoive.controller;

import com.example.microserviceconsumermoive.vo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MoiveController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MoiveController.class);

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    LoadBalancerClient loadBalancerClient;

    @GetMapping("/user/{id}")
    public User findUserById(@PathVariable Long id){
        return restTemplate.getForObject("http://microservice-provider-user/"+id,User.class);
    }

    @GetMapping("/log-user-instance")
    public void logUserInstance(){
        ServiceInstance choose = loadBalancerClient.choose("microservice-provider-user");
        MoiveController.LOGGER.info("{}:{}:{}",choose.getServiceId(),choose.getHost(),choose.getPort());
    }
}
