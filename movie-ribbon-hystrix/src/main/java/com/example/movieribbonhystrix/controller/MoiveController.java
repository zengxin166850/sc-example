package com.example.movieribbonhystrix.controller;

import com.example.movieribbonhystrix.vo.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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
    @HystrixCommand(fallbackMethod = "findByIdFallBack",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000"),
            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds",value = "10000")
    })
    public User findUserById(@PathVariable Long id){

        return restTemplate.getForObject("http://microservice-provider-user/"+id,User.class);
    }

    @GetMapping("/log-user-instance")
    public void logUserInstance(){
        ServiceInstance choose = loadBalancerClient.choose("microservice-provider-user");
        MoiveController.LOGGER.info("{}:{}:{}",choose.getServiceId(),choose.getHost(),choose.getPort());
    }

    public User findByIdFallBack(Long id){
        User user = new User();
        user.setId(-1L);
        user.setName("默认用户");
        return user;
    }
}
