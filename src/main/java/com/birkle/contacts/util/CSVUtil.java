package com.birkle.contacts.util;

import static org.apache.commons.lang3.StringUtils.isBlank;

import com.birkle.contacts.entity.Party;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class CSVUtil {

  private static final Logger log = LogManager.getLogger(CSVUtil.class);

  public List<Party> readPartiesFromCSV(String fileName) {
    List<Party> partyList = new ArrayList<>();

    ClassLoader classLoader = getClass().getClassLoader();
    InputStream inputStream = classLoader.getResourceAsStream(fileName);

    if (inputStream == null) {
      throw new IllegalArgumentException("File not found! " + fileName);
    }

    try (InputStreamReader streamReader =
        new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(streamReader)) {

      // read the first line from CSV file
      String line = br.readLine();

      // loop until all lines are read
      while (line != null) {

        // using split method to load a string array with comma as a delimiter
        String[] attributes = line.split(",");

        Party party = createParty(attributes);

        partyList.add(party);

        // read next line before looping
        line = br.readLine();
      }


    } catch (IOException e) {
      e.printStackTrace();
    }

    return partyList;
  }

  public boolean isFileNameValid(String fileName) {
    return !isBlank(fileName);
  }

  private Party createParty(String[] metadata) {
    String firstName = metadata[0];
    String lastName = metadata[1];
    String email = metadata[2];
    String phoneNumber = metadata[3];

    return Party.builder()
        .firstName(firstName)
        .lastName(lastName)
        .email(email)
        .phoneNumber(phoneNumber)
        .build();
  }
}
