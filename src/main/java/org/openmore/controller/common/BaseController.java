package org.openmore.controller.common;

import org.openmore.dto.common.ErrorResponseEntity;
import org.openmore.exception.common.InvalidateParamsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by michael on 2017/6/16.
 */

public class BaseController {
    @ExceptionHandler
    public ResponseEntity handlerException(HttpServletRequest request, Exception ex) {
        if(ex instanceof InvalidateParamsException){
            InvalidateParamsException exception = (InvalidateParamsException)ex;
            return ErrorResponseEntity.buildToResponseEntity(-1, exception.getMsg());
        }else{
            return ErrorResponseEntity.buildToResponseEntity(-1, "请求失败");
        }
    }
}
