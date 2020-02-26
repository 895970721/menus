package com.crf.menu.interceptor;

/**
 * <p>Class: TokenInterceptor</p>
 *
 * @author GodDai
 * @version 1.0.0
 * @since 2019/7/19 15:11
 */

import com.alibaba.fastjson.JSONObject;
import com.crf.menu.enums.StatusCode;
import com.crf.menu.response.BaseResponse;
import com.crf.menu.utils.CheckToken;
import com.crf.menu.utils.UserTokenUtilImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * 此拦截器通过{@link CheckToken}注解判断是否验证token<p></p>
 * 如果需要验证则验证
 * <p></p>
 * 如果验证成功和不需要验证，则返回true,如果验证失败<br>
 * 则返回false拒绝请求.
 */
@Component
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private UserTokenUtilImpl tokenUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        //取到映射到的方法
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        //如果此方法有checktoken注解
        if(method.isAnnotationPresent(CheckToken.class)){
            if(method.getAnnotation(CheckToken.class).required()){
                String token = null;
                //如果没有token
                if(request.getParameter("token").isEmpty()){
                    returnJson(response, StatusCode.UserNoLogin.getCode(), StatusCode.UserNoLogin.getMsg());
                    return false;
                }else{
                    token = request.getParameter("token");
                }
                //如果token验证成功
                if (tokenUtil.verifie(token)) {
                    return true;
                }else{
                    if (tokenUtil.isExpire(token)){
                        returnJson(response, StatusCode.UserLongTimeNoLogin.getCode(), StatusCode.UserLongTimeNoLogin.getMsg());
                        return false;
                    }else{
                        returnJson(response, 10002, "token验证错误");
                        log.error("token验证错误");
                        return false;
                    }
                }
            }else
                return true;
        }else
            return true;

    }

    private void returnJson(HttpServletResponse response, int code, String msg) throws Exception {
        BaseResponse resultJson = new BaseResponse(code, msg);
        PrintWriter writer = null;

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json , charset=utf-8");

        resultJson.setCode(code);
        resultJson.setMsg(msg);

        try {
            writer = response.getWriter();
            writer.print(JSONObject.toJSONString(resultJson));
        } catch (IOException e) {
            log.error("response error", e);
        } finally {
            if (writer != null)
                writer.close();
        }
    }
}
