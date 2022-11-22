package com.birkle.contacts.conf;

import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class RestLoggingService {

  private static final Logger log = LogManager.getLogger(RestLoggingService.class);

  public void intercept(HttpServletRequest request, HttpServletResponse response, Object body) {
    traceRequest(request);
    traceResponse(response, body);
  }

  /**
   * Method is used to format and log http requests made from the client to application
   */
  private void traceRequest(HttpServletRequest request) {
    log.info("===========================request begin===========================");
    log.info("URI         : {}", request.getRequestURL());
    log.info("Method      : {}", request.getMethod());
    log.info("Headers     : {}", getRequestHeaders(request));
    log.info("===========================request end===========================");
  }

  private void traceResponse(HttpServletResponse response, Object body) {
    log.info("===========================response begin===========================");
    log.info("Status code  : {}", response.getStatus());
    log.info("Headers      : {}", getResponseHeaders(response));
    log.info("Response body: {}", body);
    log.info("===========================response end===========================");
  }

  /**
   * Method is used to format and log http responses made from the application to the client
   */
  private Map<String, String> getRequestHeaders(HttpServletRequest request) {
    Map<String, String> headers = new HashMap<>();
    Enumeration<String> headerNames = request.getHeaderNames();

    while (headerNames.hasMoreElements()) {
      String headerName = headerNames.nextElement();
      headers.put(headerName, request.getHeader(headerName));
    }

    return headers;
  }

  private Map<String, String> getResponseHeaders(HttpServletResponse response) {
    Map<String, String> headers = new HashMap<>();
    Collection<String> headerMap = response.getHeaderNames();

    for (String str : headerMap) {
      headers.put(str, response.getHeader(str));
    }

    return headers;
  }
}