# Code Auto Generator

## 介绍
自动生成ssm-openmore-template项目里各层架构中的代码(未实现)

## 主要框架
* 自动生成controller层代码及REST API接口文档，[Entity]Controller.java
* 自动生成dto层代码，[Entity]Dto.java
* 自动生成Service层代码，[Entity]Service.java和[Entity]ServiceImpl.java

### Controller及文档注解模板
UserController.java
```java
package org.openmore.controller.api;

import io.swagger.annotations.*;
import org.openmore.controller.common.BaseController;
import org.openmore.dto.common.ErrorResponseEntity;
import org.openmore.entity.User;
import org.openmore.exception.common.InvalidateParamsException;
import org.openmore.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.openmore.dto.api.UserDto;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

/**
 * Created by michael on 2017/6/16.
 */
@Api(value = "/user", tags = "UserApi", description = "用户信息接口")
@RequestMapping(value = "/user", produces = {APPLICATION_JSON_UTF8_VALUE})
@RestController
public class UserController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @ApiOperation(value = "根据id获取用户信息,不包含密码", response = UserDto.class)
    @ApiResponses(value = { @ApiResponse(code = 400, message = "请求失败：找不到id=2的用户", response = ErrorResponseEntity.class) })
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity getUserById(@PathVariable @ApiParam(required = true, value = "用户id") Long id)
    {
        logger.debug(">> getUserById");
        UserDto user = userService.getUserProfile(id);
        if(user == null){
            throw new InvalidateParamsException("请求失败：找不到id=" + id + "的用户");
        }
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @ApiOperation(value = "检索用户信息，返回结果列表", response = User.class, responseContainer = "List")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity searchUser(@RequestParam(required = false)  @ApiParam("用户id")  Long id,
                                     @RequestParam(required = false)  @ApiParam("用户名") String username,
                                     @RequestParam(required = false)  @ApiParam("邮箱") String email)
    {
        logger.debug(">> searchUser");
        UserDto[] searchResult = userService.searchUser(id, username, email);
        return new ResponseEntity(searchResult, HttpStatus.OK);
    }

    @ApiOperation("更新用户")
    @ApiResponses(value = { @ApiResponse(code = 400, message = "请求失败：更新的数据不存在", response = ErrorResponseEntity.class),
                            @ApiResponse(code = 400, message = "请求失败：数据更新失败", response = ErrorResponseEntity.class) })
    @RequestMapping(method = RequestMethod.PUT, value = "{id}")
    public void updateUser(@PathVariable @ApiParam(value = "用户id", required = true) Long id,
                           @RequestBody @ApiParam(value = "新用户信息", required = true) User user)
    {
        logger.debug(">> updateUser");
        User entity = userService.getById(id);
        if(entity == null){
            throw new InvalidateParamsException("请求失败：更新的数据不存在");
        }
        userService.update(id, user);
    }

    @ApiOperation("创建用户")
    @ApiResponses(value = { @ApiResponse(code = 400, message = "请求失败：数据创建失败", response = ErrorResponseEntity.class) })
    @RequestMapping(method = RequestMethod.POST, consumes = {APPLICATION_JSON_UTF8_VALUE})
    public void createUser(@RequestBody @ApiParam(value = "创建用户", required = true) User user)
    {
        logger.debug(">> createUser");
        userService.create(user);
    }

    @ApiOperation(value = "删除指定用户")
    @ApiResponses(value = { @ApiResponse(code = 400, message = "请求失败：数据删除失败", response = ErrorResponseEntity.class) })
    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public void deleteUser(@PathVariable @ApiParam(value = "用户id", required = true) Long id)
    {
        logger.debug(">> deleteUser");
        userService.deleteById(id);
    }
}

```

### dto层代码模板
UserDto.java
```java
package org.openmore.dto.api;

/**
 * Created by michael on 2017/6/16.
 */
public class UserDto{
    private int user_id;
    private String username;
    private String authKey;
    private String passwordHash;
    private String passworkResetToken;
    private String email;
    private int status;
    private String createTime;
    private String updateTime;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPassworkResetToken() {
        return passworkResetToken;
    }

    public void setPassworkResetToken(String passworkResetToken) {
        this.passworkResetToken = passworkResetToken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}

```

### Service层代码模板
UserService.java
```java
package org.openmore.service;

import org.openmore.dto.api.UserDto;
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

    UserDto[] searchUser(Long id, String username, String email);

    void update(Long id, User user);

    void create(User user);

    void deleteById(Long id);

    UserDto getUserProfile(long uid);
}

```
UserServiceImpl.java
```java
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
    public UserDto getById(long uid) {
        logger.debug(">> getById");
        return userDao.getUserById(uid);
    }

    @Override
    public UserDto[] searchUser(Long id, String username, String email) {
        logger.debug(">> searchUser");
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

```
