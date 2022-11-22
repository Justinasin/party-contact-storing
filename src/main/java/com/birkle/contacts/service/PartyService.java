package com.birkle.contacts.service;

import com.birkle.contacts.entity.Party;
import com.birkle.contacts.repository.PartyRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PartyService {

  @Autowired
  private PartyRepository partyRepository;

  public Page<Party> getAllParties(Pageable pageable) {

    return partyRepository.findAll(pageable);
  }

  public Optional<Party> getPartyByFirstName(String firstName) {

    return partyRepository.findByFirstName(firstName);
  }
}