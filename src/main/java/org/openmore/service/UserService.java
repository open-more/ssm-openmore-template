package org.openmore.service;

import org.openmore.dto.UserDto;
import org.openmore.entity.User;

/**
 * Created by michael on 2017/6/16.
 */
public interface UserService {
    /**
     * 根据uid获得用户entity
     * @param uid 用户id
     * @return 返回User entity
     */
    User getById(long uid);

    UserDto getUserProfile(long uid);
}
