package org.openmore.service;

import org.openmore.dto.UserDto;
import org.openmore.entity.User;

/**
 * Created by michael on 2017/6/16.
 */
public interface UserService {
    /**
     * 返回用户
     * @param uid
     * @return
     */
    User getById(long uid);

    UserDto getUserProfile(long uid);
}
