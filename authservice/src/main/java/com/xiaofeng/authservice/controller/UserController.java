package com.xiaofeng.authservice.controller;

import com.xiaofeng.authservice.dto.RespDTO;
import com.xiaofeng.authservice.entity.User;
import com.xiaofeng.authservice.service.UserService;
import com.xiaofeng.authservice.util.BPwdEncoderUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Created by fangzhipeng on 2017/7/10.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation(value = "注册", notes = "username和password为必选项")
    @RequestMapping(value = "/registry",method = RequestMethod.POST)
    public User createUser(@RequestBody User user){
        //参数判读省略,判读该用户在数据库是否已经存在省略
        String entryPassword= BPwdEncoderUtils.BCryptPassword(user.getPassword());
        user.setPassword(entryPassword);
        return userService.createUser(user);
    }

    @ApiOperation(value = "登录", notes = "username和password为必选项")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public RespDTO login(@RequestParam String username , @RequestParam String password){
        //参数判读省略

      return   userService.login(username,password);
    }

    @ApiOperation(value = "根据用户名获取用户", notes = "根据用户名获取用户")
    @RequestMapping(value = "/{username}",method = RequestMethod.POST)
    @PreAuthorize("hasRole('USER')")
   // @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    public RespDTO getUserInfo(@PathVariable("username") String username){
        //参数判读省略
        User user=  userService.getUserInfo(username);
        return RespDTO.onSuc(user);
    }


}
