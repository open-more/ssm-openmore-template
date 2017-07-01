package org.openmore.filter;

import org.openmore.utils.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class RequestFilter extends BaseAbstractFilter {

	private static Logger logger=null;

	public void init(FilterConfig config) {
		logger = LoggerFactory.getLogger(RequestFilter.class);
	}

	public void destroy() {
		logger = null;
	}

	public static String getParamsString(Map<String, String[]> params) {
		if (params == null || params.isEmpty())
			return "";

		StringBuilder builder = new StringBuilder();
		builder.append("?");
		

		for (String key : params.keySet()) {
			builder.append(key).append("=").append(params.get(key)[0])
					.append("&");

		}
		builder.deleteCharAt(builder.lastIndexOf("&"));

		return builder.toString();
	}

	@Override
	public void doFilter(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain,
			HttpSession session, String method, String url)
			throws IOException, ServletException {
		logger.debug("Accept:{}", request.getHeader("Accept"));
		logger.debug("Content-Type:{}", request.getHeader("Content-Type"));
		long startTime = System.currentTimeMillis();
		logger.debug("拦截到请求:{} {}", method, request.getRequestURI());

		// ------- 开始校验 -------
		String sign = request.getHeader("sign");
		String time = request.getHeader("timestamp");
		String nonce = request.getHeader("nonce");
		String key = request.getHeader("app_key");
		String encrypt = request.getHeader("encrypt");

		if(Math.abs(startTime / 1000 - Long.parseLong(time)) > 60){
			logger.debug("请求时间戳超过60秒");

		}

		String secret = "";

		if(key.equals("app_android_openmore_001")){
			secret = "hahahaha";
		}

		BodyReadHttpServletRequestWrapper wrapper = new BodyReadHttpServletRequestWrapper(request);
		String bodyJson = wrapper.getJsonPararms();

		String unsignString = secret + nonce + request.getMethod() + request.getRequestURI() + bodyJson + time;
		String mysign = "";
		try {
			sign = SecurityUtils.md5(unsignString);
		}catch (Exception e){
			logger.debug("md5加密失败");
		}

		if(sign.equals(mysign)){

		}

		long endTime = System.currentTimeMillis();
		logger.debug("请求: {} {} 结果:{}", method, url, response.getStatus());
		logger.debug("花费时间：" + (endTime - startTime) + "ms");
	}
}