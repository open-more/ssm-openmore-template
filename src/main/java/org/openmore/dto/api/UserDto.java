package org.openmore.dto.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by michael on 2017/6/16.
 */

@ApiModel("用户显示模型")
public class UserDto{
    @ApiModelProperty(value="用户id")
    private int user_id;
    @ApiModelProperty(value="用户名")
    private String username;
    @ApiModelProperty(value="用户授权码")
    private String authKey;
    @ApiModelProperty(value="用户密码")
    private String passwordHash;
    @ApiModelProperty(value="用户重置密码token")
    private String passworkResetToken;
    @ApiModelProperty(value="邮件")
    private String email;
    @ApiModelProperty(value="状态")
    private int status;
    @ApiModelProperty(value="状态名")
    private String statusName;
    @ApiModelProperty(value="创建时间")
    private String createTime;
    @ApiModelProperty(value="更新时间")
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

    public String getStatusName() {
        if(status == 10){
            return "ok";
        }else{
            return "not ok";
        }
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
