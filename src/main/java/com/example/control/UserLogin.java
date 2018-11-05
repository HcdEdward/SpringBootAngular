package com.example.control;

import com.example.mapper.ShopUserMapper;
import com.example.model.ShopUser;
import com.example.util.JwtUtil;
import com.example.vo.jsonVO.Result;
import com.example.vo.jsonVO.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserLogin {
    @Autowired
    ShopUserMapper shopUserMapper;
    @PostMapping("/login")  //获取证书token
    public Result login(String username, String password){
        ShopUser shopUser = shopUserMapper.findShopUserByUsername(username);
        if (shopUser == null) {
            return ResultGenerator.genFailResult("密码错误！");
        }
        String token = JwtUtil.sign(username, password);
        return ResultGenerator.genSuccessResult().setData(token);
    }
}
