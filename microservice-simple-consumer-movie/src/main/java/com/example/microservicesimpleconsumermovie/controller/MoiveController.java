package com.example.microservicesimpleconsumermovie.controller;

import com.example.microservicesimpleconsumermovie.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MoiveController {

    @Autowired
    RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate(){
        return  new RestTemplate();
    }
    @GetMapping("/user/{id}")
    public User findUserById(@PathVariable Long id){
        return restTemplate.getForObject("http://localhost:8080/"+id,User.class);
    }
}
