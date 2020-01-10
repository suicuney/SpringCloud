package com.xiaofeng.authservice.client;

import com.xiaofeng.authservice.client.hystrix.AuthServiceHystrix;
import com.xiaofeng.authservice.entity.JWT;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Created by fangzhipeng on 2017/5/27.
 */

@FeignClient(value = "authservice")
public interface AuthServiceClient {
    @RequestMapping(value = "/oauth/token",method = RequestMethod.POST)
    JWT getToken(@RequestHeader(value = "Authorization") String authorization, @RequestParam("grant_type") String type,
                 @RequestParam("username") String username, @RequestParam("password") String password);
}



