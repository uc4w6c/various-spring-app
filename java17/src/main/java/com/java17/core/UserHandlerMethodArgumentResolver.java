package com.java17.core;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class UserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

  @Override
  public boolean supportsParameter(MethodParameter methodParameter) {
    return methodParameter.getParameterAnnotation(UserAnnotation.class) != null;
  }

  @Override
  public Object resolveArgument(
      MethodParameter methodParameter,
      ModelAndViewContainer modelAndViewContainer,
      NativeWebRequest nativeWebRequest,
      WebDataBinderFactory webDataBinderFactory)
      throws Exception {
    return new User("taro", 12);
  }
}
