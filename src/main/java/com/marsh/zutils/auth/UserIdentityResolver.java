package com.marsh.zutils.auth;


import com.marsh.zutils.constant.BaseConstant;
import com.marsh.zutils.exception.BaseBizException;
import com.marsh.zutils.exception.BaseError;
import com.marsh.zutils.helper.JwtHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;


@Slf4j
public class UserIdentityResolver implements HandlerMethodArgumentResolver {

    private final JwtHelper jwtHelper;

    public UserIdentityResolver(JwtHelper jwtHelper) {
        this.jwtHelper = jwtHelper;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return UserIdentity.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String bear = webRequest.getHeader(BaseConstant.AUTH_HEADER);

        if (bear == null) {
            throw new BaseBizException(BaseError.LACK_ACCESS_TOKEN);
        }

        String token = bear.substring(7);
        var claims = jwtHelper.decode(token);

        return ((UserIdentity) parameter.getParameterType().getDeclaredConstructor().newInstance()).identity(claims);
    }


}
