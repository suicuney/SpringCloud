package com.xiaofeng.authservice.client.hystrix;


import com.xiaofeng.authservice.client.AuthServiceClient;
import com.xiaofeng.authservice.entity.JWT;
import org.springframework.stereotype.Component;

/**
 * Created by fangzhipeng on 2017/5/31.
 */
@Component
public class AuthServiceHystrix implements AuthServiceClient {
    @Override
    public JWT getToken(String authorization, String type, String username, String password) {
        System.out.println("--------opps getToken hystrix---------");
        return null;
    }
}
