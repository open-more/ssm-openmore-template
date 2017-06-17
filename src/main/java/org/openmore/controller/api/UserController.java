package org.openmore.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.openmore.controller.common.BaseController;
import org.openmore.dto.common.ErrorResponseEntity;
import org.openmore.exception.common.InvalidateParamsException;
import org.openmore.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.openmore.dto.UserDto;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

/**
 * Created by michael on 2017/6/16.
 */
@RestController
@RequestMapping(value = "/user", produces = {APPLICATION_JSON_UTF8_VALUE})
@Api(value = "/user", tags = "UserApi", description = "用户信息接口")
public class UserController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{uid}", method = RequestMethod.GET)

    @ApiOperation(
            value = "根据id获取用户信息,不包含密码",
            response = UserDto.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "指定id的用户不存在",
                    response = ErrorResponseEntity.class)
    })
    public ResponseEntity getUserById(@PathVariable Long uid)
    {
        if(uid == null){
            throw new InvalidateParamsException("请求失败：uid参数不能为空");
        }

        UserDto user = userService.getUserProfile(uid);
        if(user == null){
            throw new InvalidateParamsException("请求失败：找不到id=" + uid + "的用户");
        }
        return new ResponseEntity(user, HttpStatus.OK);
    }
}
