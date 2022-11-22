package com.birkle.contacts.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class RestResponseInterceptor implements ResponseBodyAdvice<Object> {

  @Autowired
  private RestLoggingService restLoggingService;


  /**
   * Method returns true, that means beforeBodyWrite method will be invoked
   */
  @Override
  public boolean supports(MethodParameter returnType,
      Class<? extends HttpMessageConverter<?>> converterType) {

    return true;
  }

  /**
   * Before returning response body to the client, application intercepts request and response
   * to log information
   */
  @Override
  public Object beforeBodyWrite(Object body,
      MethodParameter returnType,
      MediaType selectedContentType,
      Class<? extends HttpMessageConverter<?>> selectedConverterType,
      ServerHttpRequest request,
      ServerHttpResponse response) {

    restLoggingService.intercept(((ServletServerHttpRequest) request).getServletRequest(),
        ((ServletServerHttpResponse) response).getServletResponse(), body);

    return body;
  }
}