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
    private String statusName;
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
