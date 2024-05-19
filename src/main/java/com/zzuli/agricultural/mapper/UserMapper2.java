package com.zzuli.agricultural.mapper;

import com.zzuli.agricultural.model.User;

import java.util.List;
import java.util.Map;

public interface UserMapper2 {
    void insertUser(User user);
    void deleteUser(Integer id);
    void updateUser(User user);
    User selectUser(Integer id);
    List<User> selectAllUsers();
    List<User> selectUserByParams(Map<String,Object> params);
}
