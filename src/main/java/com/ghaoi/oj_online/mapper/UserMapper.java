package com.ghaoi.oj_online.mapper;

import com.ghaoi.oj_online.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    // 根据用户名查找用户
    User search(String username);

    // 添加用户
    int addUser(User user);
}
