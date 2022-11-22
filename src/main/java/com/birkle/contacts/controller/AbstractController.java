package com.birkle.contacts.controller;

import static com.birkle.contacts.util.ConstantEnum.CODE;
import static com.birkle.contacts.util.ConstantEnum.DATA;
import static com.birkle.contacts.util.ConstantEnum.ERROR;
import static com.birkle.contacts.util.ConstantEnum.MESSAGE;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class AbstractController {

  protected ResponseEntity<Object> createResponse(final Object message,
      final HttpHeaders httpHeaders,
      final HttpStatus httpStatus) {

    Map<String, Object> responseMap = new HashMap<>();
    responseMap.put(DATA.getValue(), message);

    return new ResponseEntity<>(responseMap,
        httpHeaders,
        httpStatus);
  }

  protected ResponseEntity<Object> createResponse(final Object message,
      final HttpStatus httpStatus) {

    return createResponse(message, null, httpStatus);
  }

  protected ResponseEntity<Object> createErrorResponse(final String message,
      final HttpStatus httpStatus) {

    Map<Object, Object> responseBody = new HashMap<>();
    responseBody.put(CODE.getValue(), httpStatus.value());
    responseBody.put(MESSAGE.getValue(), message);

    return new ResponseEntity<>(Collections.singletonMap(ERROR.getValue(), responseBody),
        httpStatus);
  }
}