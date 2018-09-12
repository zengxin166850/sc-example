package com.example.microconsumermoviefeign.service;

import com.example.microconsumermoviefeign.vo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "microservice-provider-user")
public interface UserFeignClient {

    @GetMapping(value = "/{id}")
    public User findone(@PathVariable("id")  Long id);
}
