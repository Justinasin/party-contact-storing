package com.birkle.contacts.utils;

import static com.google.common.collect.Lists.newArrayList;

import com.birkle.contacts.entity.Party;
import java.util.List;

public class UnitTest {

  protected final String FIRST_NAME = "FIRST_NAME";
  protected final String LAST_NAME = "LAST_NAME";
  protected final String EMAIL = "EMAIL";
  protected final String PHONE_NUMBER = "PHONE_NUMBER";

  protected List<Party> createPartyList() {

    return newArrayList(createParty());
  }

  protected Party createParty() {

    return Party.builder()
        .firstName(FIRST_NAME)
        .lastName(LAST_NAME)
        .email(EMAIL)
        .phoneNumber(PHONE_NUMBER)
        .build();
  }
}