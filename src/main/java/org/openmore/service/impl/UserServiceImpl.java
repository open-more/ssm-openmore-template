package org.openmore.service.impl;

import org.openmore.dao.UserMapper;
import org.openmore.dto.api.UserDto;
import org.openmore.entity.User;
import org.openmore.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created UserServiceImpl michael on 2017/6/16.
 */
@Service
public class UserServiceImpl implements UserService{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    // 注入Service依赖
    @Autowired
    private UserMapper userDao;

    @Transactional
    @Override
    public User getById(long uid) {
        logger.debug(">> getById");
        return userDao.getUserById(uid);
    }

    @Override
    public UserDto[] searchUser(Long id, String username, String email) {
        logger.debug(">> searchUser");

        logger.debug("id = " + id + ", username = " + username + ", email = " + email);
        return null;
    }

    @Override
    public void update(Long id, User user) {
        logger.debug(">> update");
    }

    @Override
    public void create(User user) {
        logger.debug(">> create");
    }

    @Override
    public void deleteById(Long id) {
        logger.debug(">> deleteById");
    }

    @Override
    public UserDto getUserProfile(long uid) {
        logger.debug(">> getUserProfile");
        User user = userDao.getUserById(uid);
        UserDto dto = new UserDto();
        dto.setUser_id(user.getId());
        BeanUtils.copyProperties(user, dto);
        return dto;
    }
}
