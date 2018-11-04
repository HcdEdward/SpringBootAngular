package com.example.mapper;

import com.example.model.ShopUser;
import org.springframework.stereotype.Component;

@Component
public interface ShopUserMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(ShopUser record);

    int insertSelective(ShopUser record);

    ShopUser selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(ShopUser record);

    int updateByPrimaryKey(ShopUser record);

    ShopUser findShopUserByUsername(String username);
}