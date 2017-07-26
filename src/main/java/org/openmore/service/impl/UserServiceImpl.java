package org.openmore.service.impl;

import org.apache.commons.beanutils.BeanUtils;
import org.openmore.dto.api.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import org.openmore.service.UserService;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openmore.dao.UserMapper;
import org.openmore.entity.User;

/**
 * THIS IS AUTO GENERATED SOURCE CODE
 *   Created by org.openmore
 *      on 2017-07-01
 */
@Service
public class UserServiceImpl implements UserService{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    // 注入Service依赖
    @Autowired
    private UserMapper userDao;

    /**
     * 根据id获得用户 Dto对象
     * @param id
     * @return UserDto
     */
    @Transactional
    public UserDto getUserDtoById(Integer id) throws IllegalAccessException, InvocationTargetException
    {
        logger.debug(">> getUserDtoById(Integer id) id = " + id);
        User entity = userDao.selectByPrimaryKey(id);
        UserDto dto = new UserDto();
        BeanUtils.copyProperties(dto, entity);
        return dto;
    }

    /**
     * 根据id获得用户 Db Entity对象
     * @param id
     * @return User Db Entity
     */
    @Transactional
    public User getEntityById(Integer id)
    {
        logger.debug(">> getEntityById(Integer id)");
        return userDao.selectByPrimaryKey(id);
    }

    /**
     * 根据指定的参数对数据进行检索
     * @return List<UserDto>结果列表
     */
    @Transactional
    public List<UserDto> searchUser(User user) throws IllegalAccessException, InvocationTargetException
    {
        logger.debug(">> searchUser(User user)");
        List<User> searchResult;
        if(user == null){
            searchResult = userDao.selectAll();
        }else{
            searchResult = userDao.select(user);
        }

        List<UserDto> dtoResult = new ArrayList<>();
        for(User u : searchResult)
        {
            UserDto dto = new UserDto();
            BeanUtils.copyProperties(dto, u);
            dtoResult.add(dto);
        }
        return dtoResult;
    }

    /**
     * 创建用户对象
     * @param user 用户 Db Entity对象
     */
    @Transactional
    public void create(User user) throws Exception
    {
        logger.debug(">> create(User user)");
        try{
            userDao.insertSelective(user);
        }catch (Exception e){
            logger.error(e.getMessage());
            throw e;
        }
    }

    /**
     * 删除指定id的用户对象
     * @param id 用户id
     */
    @Transactional
    public void deleteById(Integer id)
    {
        logger.debug(">> deleteById(Integer id)");
        userDao.deleteByPrimaryKey(id);
    }

    /**
     * 更新指定id的用户对象为user
     * @param user 需要更新的Db Entity对象
     */
    @Transactional
    public void update(User user) throws Exception
    {
        logger.debug(">> update(User user");
        // updateByPrimaryKeySelective only update field not null.
        try{
            userDao.updateByPrimaryKeySelective(user);
        }catch (Exception e){
            logger.error(e.getMessage());
            throw e;
        }
    }
}