package com.birkle.contacts.util;

public enum ConstantEnum {

  DATA("data"),
  ERROR("error"),
  CODE("code"),
  MESSAGE("message");
  private final String value;

  ConstantEnum(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}