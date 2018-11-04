package com.example.mapper;

import com.example.model.shop_user;

public interface shop_userMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(shop_user record);

    int insertSelective(shop_user record);

    shop_user selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(shop_user record);

    int updateByPrimaryKey(shop_user record);
}