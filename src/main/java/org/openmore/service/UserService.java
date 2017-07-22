package org.openmore.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import org.openmore.dto.UserDto;
import org.openmore.entity.User;

/**
 * THIS IS AUTO GENERATED SOURCE CODE
 *   Created by org.openmore
 *      on 2017-07-01
 */
public interface UserService {

    /**
     * 根据id获得用户 Dto对象
     * @param id
     * @return UserDto
     */
    UserDto getUserDtoById(Integer id) throws IllegalAccessException, InvocationTargetException;

    /**
     * 根据id获得用户 Db Entity对象
     * @param id
     * @return User Db Entity
     */
    User getEntityById(Integer id);

    /**
     * 根据指定的参数对数据进行检索
     * @return List<UserDto>结果列表
     */
    List<UserDto> searchUser(User user) throws IllegalAccessException, InvocationTargetException;

    /**
     * 创建用户对象
     * @param user 用户 Db Entity对象
     */
    void create(User user) throws Exception;

    /**
     * 删除指定id的用户对象
     * @param id 用户id
     */
    void deleteById(Integer id);

    /**
     * 更新指定id的用户对象为user
     * @param user 需要更新的Db Entity对象
     */
    void update(User user) throws Exception;
}