package com.co.challenge.devsubankperson.services;

import com.co.challenge.devsubankperson.web.model.PersonDto;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

public interface PersonService {

    Optional<PersonDto> getPersonById(UUID personId) ;

    PersonDto createNewPerson(PersonDto personDto);

    Optional<PersonDto> updatePersonBuyId(UUID personId, PersonDto personDto);

    Optional<PersonDto> patchPersonById(UUID personId, PersonDto personDto);

    Boolean removePersonById(UUID personId);

    Page<PersonDto> listPersons(String firstLastName, String secondLastName, String address, Long phoneNumber,
                                Integer pageNumber, Integer pageSize);
}
