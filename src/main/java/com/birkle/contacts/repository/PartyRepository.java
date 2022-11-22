package com.birkle.contacts.repository;

import com.birkle.contacts.entity.Party;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartyRepository extends JpaRepository<Party, Long> {

  Optional<Party> findByFirstName(String firstName);
}