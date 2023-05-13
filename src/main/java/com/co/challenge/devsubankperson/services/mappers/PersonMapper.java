package com.co.challenge.devsubankperson.services.mappers;

import com.co.challenge.devsubankperson.repositories.domain.Person;
import com.co.challenge.devsubankperson.web.model.PersonDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface PersonMapper {

    PersonDto personToPersonDto(Person person);

    Person personDtoToPerson(PersonDto personDto);
}
