package com.birkle.contacts.controller;

import static java.lang.String.format;
import static org.springframework.data.domain.Sort.Direction.ASC;

import com.birkle.contacts.entity.Party;
import com.birkle.contacts.service.PartyService;
import java.util.Optional;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping(value = "/parties", produces = MediaType.APPLICATION_JSON_VALUE)
public class PartyController extends AbstractController {

  @Autowired
  private PartyService partyService;

  @GetMapping(value = "")
  public ResponseEntity<Object> getParties(
      @PageableDefault(size = 5)
      @SortDefault.SortDefaults({
          @SortDefault(sort = "firstName", direction = ASC)
      })
      Pageable pageable) {

    Page<Party> page = partyService.getAllParties(pageable);
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add("X-Total-Count", String.valueOf(page.getTotalElements()));

    return createResponse(page.getContent(), httpHeaders, HttpStatus.OK);
  }

  @GetMapping(value = "/{firstName}")
  public ResponseEntity<Object> getPartyByName(
      @PathVariable
      @NotBlank
      @Size(max = 50) String firstName) {

    Optional<Party> partyOptional = partyService.getPartyByFirstName(firstName);

    ResponseEntity<Object> responseEntity;

    if (partyOptional.isPresent()) {
      responseEntity = createResponse(partyOptional.get(), HttpStatus.OK);

    } else {
      responseEntity = createErrorResponse(
          format("Party with first name: '%s' not found", firstName),
          HttpStatus.NOT_FOUND);
    }

    return responseEntity;
  }
}