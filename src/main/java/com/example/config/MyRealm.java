package com.example.config;

import com.example.exception.MyException;
import com.example.mapper.ShopUserMapper;
import com.example.model.ShopUser;
import com.example.util.JwtUtil;
import com.example.vo.tokenVO.JwtToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyRealm extends AuthorizingRealm {

    @Autowired
    ShopUserMapper shopUserMapper;

    /**
     * 必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //String username = JwtUtil.getUsername(principals.toString());
       // SysUser user = sysUserService.findByUserName(username);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        return simpleAuthorizationInfo;
    }

    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws MyException{
        String token = (String) auth.getCredentials();
        // 解密获得username，用于和数据库进行对比
        String username = JwtUtil.getUsername(token);
        ShopUser shopUser = shopUserMapper.findShopUserByUsername(username);
        if (username == null) {
            throw new MyException("000","token 无效","doGetAuthenticationInfo","重新注册证书！");
        }
        if (shopUser == null) {
            throw new MyException("000","用户不存在","doGetAuthenticationInfo","用户不存在！");
        }
        if (!JwtUtil.verify(token, username, shopUser.getPassword())) {
            throw new MyException("000","用户名或密码错误","doGetAuthenticationInfo","用户名或密码错误！");
        }
        return new SimpleAuthenticationInfo(token, token, "my_realm");
    }
}
