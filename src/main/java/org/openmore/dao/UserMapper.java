package org.openmore.dao;

import org.apache.ibatis.annotations.Param;
import org.openmore.entity.User;
import tk.mybatis.mapper.common.Mapper;

/**
 * Created by michael on 2017/6/16.
 */
public interface UserMapper extends Mapper<User> {
    User getUserById(@Param("uid") long uid);
}