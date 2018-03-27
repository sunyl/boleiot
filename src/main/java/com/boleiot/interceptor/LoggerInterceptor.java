package com.boleiot.interceptor;

import com.boleiot.model.LoggerEntity;
import com.boleiot.utils.JsonUtil;
import com.boleiot.utils.LoggerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class LoggerInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoggerInterceptor.class);

    private static final String LOGGER_ENTITY = "logger_entity";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        LoggerEntity entity = new LoggerEntity();
        String sessionId = request.getRequestedSessionId();
        String url = request.getRequestURI();
        Map<String, Object> param = getRequestParameter(request);
        entity.setClientIp(LoggerUtils.getCliectIp(request));
        entity.setMethod(request.getMethod());
        entity.setType(LoggerUtils.getRequestType(request));
        entity.setParamData(param);
        entity.setUri(url);
        entity.setSessionId(sessionId);
        entity.setTime(System.currentTimeMillis());
        request.setAttribute(LOGGER_ENTITY, entity);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {
        int status = response.getStatus();
        long currentTime = System.currentTimeMillis();
        LoggerEntity entity = (LoggerEntity) request.getAttribute(LOGGER_ENTITY);
        entity.setReturnTime(currentTime);
        entity.setTimeConsuming(currentTime - entity.getTime());
        entity.setHttpStatusCode(status + "");
        entity.setReturnData("----");
        //存入数据库
        logger.info("LoggerEntity:" + JsonUtil.toJson(entity));
    }

    private Map<String, Object> getRequestParameter(HttpServletRequest request) {
        if (null == request) {
            return null;
        }
        String method = request.getMethod();
        Map<String, Object> params = new HashMap<>();
        if (method.equalsIgnoreCase("GET")) {
            params.put("get_param", request.getQueryString());
        } else {
            params = getBodyData(request);
        }
        return params;
    }

    private Map<String, Object> getBodyData(HttpServletRequest request) {
        Enumeration enu = request.getParameterNames();
        Map<String, Object> params = new HashMap<>();
        while (enu.hasMoreElements()) {
            String paraName = (String) enu.nextElement();
            params.put(paraName, request.getParameter(paraName));
        }
        return params;
    }

}
