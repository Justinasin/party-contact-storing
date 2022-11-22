package com.birkle.contacts.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.birkle.contacts.entity.Party;
import com.birkle.contacts.repository.PartyRepository;
import com.birkle.contacts.utils.UnitTest;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class PartyServiceTest extends UnitTest {

  @InjectMocks
  private PartyService partyService;
  @Mock
  private PartyRepository partyRepository;

  private final Party party = createParty();
  private final List<Party> partyList = createPartyList();

  @BeforeEach
  void setUp() {
    Page<Party> pagedResponse = new PageImpl<>(partyList);
    when(partyRepository.findAll(any(Pageable.class))).thenReturn(pagedResponse);

    when(partyRepository.findByFirstName(FIRST_NAME)).thenReturn(Optional.of(party));
  }

  @Test
  void getAllPartiesTest() {
    Page<Party> pagedResponse = partyService.getAllParties(Pageable.ofSize(1));
    List<Party> partyList = pagedResponse.get().toList();

    assertNotNull(pagedResponse);

    verify(partyRepository, times(1)).findAll(any(Pageable.class));

    assertEquals(1, pagedResponse.getTotalElements());
    assertEquals(FIRST_NAME, partyList.get(0).getFirstName());
    assertEquals(LAST_NAME, partyList.get(0).getLastName());
    assertEquals(EMAIL, partyList.get(0).getEmail());
    assertEquals(PHONE_NUMBER, partyList.get(0).getPhoneNumber());
  }

  @Test
  void getPartyByFirstNameTest() {
    Optional<Party> partyOptional = partyService.getPartyByFirstName(FIRST_NAME);

    assertTrue(partyOptional.isPresent());
    verify(partyRepository, times(1)).findByFirstName(FIRST_NAME);

    assertEquals(FIRST_NAME, partyOptional.get().getFirstName());
    assertEquals(LAST_NAME, partyOptional.get().getLastName());
    assertEquals(EMAIL, partyOptional.get().getEmail());
    assertEquals(PHONE_NUMBER, partyOptional.get().getPhoneNumber());
  }
}
