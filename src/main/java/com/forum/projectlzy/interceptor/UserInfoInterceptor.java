package com.forum.projectlzy.interceptor;

import com.forum.projectlzy.entity.CurrentUserInfo;
import com.forum.projectlzy.entity.User;
import com.forum.projectlzy.utils.JwtUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截获得token中的user消息
 * @author 扬
 */
public class UserInfoInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (token != null && !token.isEmpty()) {
            User user = JwtUtil.parse(token);
            CurrentUserInfo.set(user);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        CurrentUserInfo.remove();
    }
}
