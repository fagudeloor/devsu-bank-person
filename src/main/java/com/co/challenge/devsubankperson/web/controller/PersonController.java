package com.co.challenge.devsubankperson.web.controller;


import com.co.challenge.devsubankperson.services.PersonService;
import com.co.challenge.devsubankperson.web.exceptions.NotFoundException;
import com.co.challenge.devsubankperson.web.model.PersonDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.co.challenge.devsubankperson.utils.Constants.*;
import static com.co.challenge.devsubankperson.utils.ErrorMessages.USER_NOT_FOUND;

@Slf4j
@RequiredArgsConstructor
@RestController
public class PersonController {

    private final PersonService personService;

    @GetMapping(PERSON_PATH_ID)
    public PersonDto getPersonById(@PathVariable(PERSON_ID) UUID personId){
        log.info("Into getMapping id:" + personId.toString());
        return personService.getPersonById(personId).orElseThrow(NotFoundException::new);
    }

    @GetMapping(PERSON_PATH)
    public Page<PersonDto> listPersons(@RequestParam(required = false) String firstLastName,
                                    @RequestParam(required = false) String secondLastName,
                                    @RequestParam(required = false) String address,
                                    @RequestParam(required = false) Long phoneNumber,
                                    @RequestParam(required = false) Integer pageNumber,
                                    @RequestParam(required = false) Integer pageSize){

        return personService.listPersons(firstLastName, secondLastName, address, phoneNumber, pageNumber, pageSize);
    }

    @PostMapping(PERSON_PATH)
    public ResponseEntity createNewPerson(@RequestBody PersonDto personDto){
        log.info("Into postMapping id: " + personDto.getDocumentNumber().toString());
        PersonDto createdPerson = personService.createNewPerson(personDto);

        HttpHeaders headers = new HttpHeaders();
        headers.add(LOCATION, PERSON_PATH + SLASH + createdPerson.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping(PERSON_PATH_ID)
    public ResponseEntity updatePersonById(@PathVariable(PERSON_ID) UUID personId, @RequestBody PersonDto personDto){
        log.info("Into putMapping id: " + personId.toString());

        if(personService.updatePersonBuyId(personId, personDto).isEmpty())
            throw new NotFoundException(USER_NOT_FOUND);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(PERSON_PATH_ID)
    public ResponseEntity patchPerson(@PathVariable(PERSON_ID) UUID personId, @RequestBody PersonDto personDto){
        log.info("Into patchMapping id: " + personId.toString());

        if(personService.patchPersonById(personId, personDto).isEmpty())
            throw new NotFoundException(USER_NOT_FOUND);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(PERSON_PATH_ID)
    public ResponseEntity removePerson(@PathVariable(PERSON_ID) UUID personId){
        log.info("Into deleteMapping id: " + personId.toString());
        if(!personService.removePersonById(personId)){
            throw new NotFoundException(USER_NOT_FOUND);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
