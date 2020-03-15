package com.card.mapper;

import com.card.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 *
 * Created by hongpeng.cui on 2019/3/25.
 */
@Component
public interface UserMapper {
    int deleteByPrimaryKey(long id);

    long insert(User record);

    User selectByPrimaryKey(Long id);

    List<User> selectAll();

    List<User> selectUserByParams(Map<String,Object> params);

    int updateByPrimaryKey(User record);

    int getUserCount(Map<String,Object> params);
}
