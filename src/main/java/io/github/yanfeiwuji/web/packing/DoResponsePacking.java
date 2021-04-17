package io.github.yanfeiwuji.web.packing;

import org.apache.logging.log4j.util.LambdaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Type;
import java.util.Objects;
import java.util.Optional;

/**
 * @author yanfeiwuji
 * @date 2021/4/17 12:02 下午
 */
@ControllerAdvice
public class DoResponsePacking implements ResponseBodyAdvice<Object> {


  @Autowired
  ObjToPacking objToPacking;

  private final String SPRING_RES = "org.springframework.http.ResponseEntity";

  private Class targetClass;

  @Override
  public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
    boolean result = methodParameter.hasMethodAnnotation(RequestMapping.class) && aClass.equals(MappingJackson2HttpMessageConverter.class);
    final Type genericParameterType =
      methodParameter.getGenericParameterType();
    // 过滤掉swagger
    final String typeName = genericParameterType.getTypeName();
    if (typeName.startsWith(SPRING_RES)) {
      return false;
    }
    return result;
  }

  @Override
  public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

    Class needClass = loadClass();
    if (o == null) {
      return objToPacking.doPacking(null);
    }

    if (o.getClass().equals(needClass)) {
      return o;
    } else {
      return objToPacking.doPacking(o);
    }

  }

  private Class loadClass() {
    if (targetClass != null) {
      return targetClass;
    }
    targetClass = Optional.ofNullable(objToPacking)
      .map(op -> op.doPacking(null))
      .map(o -> (Class) o.getClass())
      .orElse(Object.class);
    return targetClass;
  }
}
