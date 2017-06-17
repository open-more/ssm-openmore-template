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
