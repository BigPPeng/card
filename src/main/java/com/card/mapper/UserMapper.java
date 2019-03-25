package com.card.mapper;

import com.card.model.User;

import java.util.List;
import java.util.Map;

/**
 *
 * Created by hongpeng.cui on 2019/3/25.
 */
public interface UserMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    List<User> selectUserByParams(Map<String,Object> params);

    int updateByPrimaryKey(User record);

    int getUserCount(Map<String,Object> params);
}
