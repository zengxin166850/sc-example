package com.example.microserviceconsumermoive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MoiveMetaController {


    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping("/user-instance")
    public List<ServiceInstance> showInfo(){
        return discoveryClient.getInstances("microservice-provider-user");
    }
}
