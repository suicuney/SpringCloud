package com.xiaofeng.authservice.service;

import com.xiaofeng.authservice.client.AuthServiceClient;
import com.xiaofeng.authservice.dao.UserDao;
import com.xiaofeng.authservice.dto.LoginDTO;
import com.xiaofeng.authservice.dto.RespDTO;
import com.xiaofeng.authservice.entity.JWT;
import com.xiaofeng.authservice.entity.User;
import com.xiaofeng.authservice.exception.CommonException;
import com.xiaofeng.authservice.exception.ErrorCode;
import com.xiaofeng.authservice.util.BPwdEncoderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by fangzhipeng on 2017/7/10.
 */
@Service
public class UserService {

    @Autowired
    UserDao userDao;
    @Autowired
    AuthServiceClient authServiceClient;

    public User createUser(User user){
      return  userDao.save(user);
    }

    public User getUserInfo(String username){
        return userDao.findByUsername(username);
    }
    public RespDTO login(String username , String password){
       User user= userDao.findByUsername(username);
       if(null==user){
           throw new CommonException(ErrorCode.USER_NOT_FOUND);
       }
       if(!BPwdEncoderUtils.matches(password,user.getPassword())){
           throw new CommonException(ErrorCode.USER_PASSWORD_ERROR);
       }

        JWT jwt = authServiceClient.getToken("Basic YXV0aHNlcnZpY2U6MTIzNDU2", "password", username, password);

        // 获得用户菜单
        if(null==jwt){
            throw new CommonException(ErrorCode.GET_TOKEN_FAIL);
        }
        LoginDTO loginDTO=new LoginDTO();
        loginDTO.setUser(user);
        loginDTO.setToken(jwt.getAccess_token());
        return RespDTO.onSuc(loginDTO);
    }
}
