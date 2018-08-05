package com.example.springmvc.component;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;


/**
 * 登录用户参数解析器
 *
 * @author xiongLiang
 * @date 2018/7/19 11:29
 */
public class LoginUserParamResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return LoginUser.class.isAssignableFrom(methodParameter.getClass());
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest,
            WebDataBinderFactory webDataBinderFactory) throws Exception {
        // 从保存用户登录信息的地方获取当前用户信息，返回
        return new LoginUser();
    }
}
