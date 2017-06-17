package org.openmore.interceptors;

import org.openmore.filter.BodyReadHttpServletRequestWrapper;
import org.openmore.utils.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by michael on 2017/6/18.
 */
public class SecurityInterceptor implements HandlerInterceptor {

    private static Logger logger = LoggerFactory.getLogger(SecurityInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request == null)
            logger.debug("null");
        logger.debug("Accept:{}", request.getHeader("Accept"));
        logger.debug("Content-Type:{}", request.getHeader("Content-Type"));
        long startTime = System.currentTimeMillis();
        logger.debug("拦截到请求:{} {}", request.getMethod(), request.getRequestURI());

        // ------- 开始校验 -------
        String sign = request.getHeader("sign");
        String time = request.getHeader("timestamp");
        String nonce = request.getHeader("nonce");
        String key = request.getHeader("app_key");
        String encrypt = request.getHeader("encrypt");
        String contentType = request.getHeader("Content-Type");
        BodyReadHttpServletRequestWrapper wrapper = new BodyReadHttpServletRequestWrapper(request);
        String jsonBody = wrapper.getJsonPararms();

        logger.debug("sign = {} time = {} nonce = {} key = {} jsonBody = {} encrypt = {}", sign, time, nonce, key, jsonBody, encrypt);

        if(StringUtils.isEmpty(sign) || StringUtils.isEmpty(time)
                || StringUtils.isEmpty(nonce) || StringUtils.isEmpty(key)
                || StringUtils.isEmpty(contentType)){
            logger.error("Header缺少参数");
            response.setStatus(401);
            return false;
        }

        if(!request.getHeader("Content-Type").contains("application/json")){
            logger.error("Content-Type配置不正确");
            response.setStatus(401);
            return false;
        }

        if(Math.abs(startTime / 1000 - Long.parseLong(time)) > 60){
            logger.debug("请求时间戳超过60秒");
            response.setStatus(401);
            return false;
        }

        String secret = "";

        if(key.equals("app_android_openmore_001")){
            secret = "hahahaha";
        }

        String unsignString = secret + nonce + request.getMethod().toUpperCase() + request.getRequestURI() + jsonBody + time;
        String mysign = "";
        try {
            logger.debug("unsignString = {}", unsignString);
            mysign = SecurityUtils.md5(unsignString);
            logger.debug("sign = {}", mysign);
        }catch (Exception e){
            logger.debug("md5加密失败");
            response.setStatus(401);
            return false;
        }

        if(!sign.toUpperCase().equals(mysign.toUpperCase())){
            logger.error("signature not corrected");
            response.setStatus(401);
            return false;
        }

        long endTime = System.currentTimeMillis();
        logger.debug("请求: {} {} 结果:{}", request.getMethod(), request.getRequestURI(), response.getStatus());
        logger.debug("花费时间：" + (endTime - startTime) + "ms");

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
