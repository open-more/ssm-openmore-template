package org.openmore.exception.common;

/**
 * Created by michael on 2017/6/16.
 */
public class InvalidateParamsException extends RuntimeException {
    private int statusCode;
    private String msg;

    public InvalidateParamsException()
    {
        this("请求失败：请求参数不正确");
    }

    public InvalidateParamsException(String msg)
    {
        super();
        this.statusCode = 400;
        this.msg = msg;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
