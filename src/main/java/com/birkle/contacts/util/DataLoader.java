package com.birkle.contacts.util;

import com.birkle.contacts.entity.Party;
import com.birkle.contacts.repository.PartyRepository;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

  private static final Logger log = LogManager.getLogger(DataLoader.class);
  @Autowired
  private CSVUtil csvUtil;

  @Autowired
  private PartyRepository partyRepository;

  @Override
  public void run(ApplicationArguments args) {
    initializeDatabaseWithParties("people.csv");
  }

  private void initializeDatabaseWithParties(String fileName) {
    if (!csvUtil.isFileNameValid(fileName)) {
      log.error(
          "Database is not initialized with initial values. Check provided file name in the application");

      return;
    }

    List<Party> partyList = csvUtil.readPartiesFromCSV(fileName);

    partyRepository.saveAll(partyList);
  }
}