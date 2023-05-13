package com.co.challenge.devsubankperson.services;

import com.co.challenge.devsubankperson.repositories.PersonRepository;
import com.co.challenge.devsubankperson.repositories.domain.Person;
import com.co.challenge.devsubankperson.web.exceptions.NotFoundException;
import com.co.challenge.devsubankperson.services.mappers.PersonMapper;
import com.co.challenge.devsubankperson.web.model.PersonDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

import static com.co.challenge.devsubankperson.utils.Constants.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    @Override
    public Optional<PersonDto> getPersonById(UUID personId) throws NotFoundException {
        Optional<Person> personFound = personRepository.findById(personId);

        personFound.ifPresentOrElse( p -> {
            log.debug("person Found: " + p.toString());

        }, () -> {
            log.debug("Person not found! id" + personId);
        });

       return Optional.ofNullable(personMapper.personToPersonDto(personFound
                 .orElse(null)));
    }

    @Override
    public Page<PersonDto> listPersons(String firstLastName, String secondLastName, String address,
                                       Long phoneNumber, Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = buildPageRequest(pageNumber, pageSize);
        Page<Person> all = personRepository.findAll(pageRequest);

        return all.map(personMapper::personToPersonDto);
    }

    @Override
    public PersonDto createNewPerson(PersonDto personDto) {
        return personMapper.personToPersonDto(
                personRepository.save(personMapper.personDtoToPerson(personDto)));
    }

    @Override
    public Optional<PersonDto> updatePersonBuyId(UUID personId, PersonDto personDto) {
        AtomicReference<Optional<PersonDto>> atomicReference = new AtomicReference<>();

        personRepository.findById(personId).ifPresentOrElse(foundPerson -> {
            foundPerson.setFirstLastName(personDto.getFirstLastName());
            foundPerson.setSecondLastName(personDto.getSecondLastName());
            foundPerson.setGivenName(personDto.getGivenName());
            foundPerson.setMainAddress(personDto.getMainAddress());
            foundPerson.setSecondAddress(personDto.getSecondAddress());
            foundPerson.setPhoneNumber(personDto.getPhoneNumber());
            foundPerson.setSecondPhoneNumber(personDto.getSecondPhoneNumber());
            atomicReference.set(Optional.of(personMapper.personToPersonDto(personRepository.save(foundPerson))));
        }, () -> atomicReference.set(Optional.empty()));
        return atomicReference.get();
    }

    @Override
    public Optional<PersonDto> patchPersonById(UUID personId, PersonDto personDto) {
        AtomicReference<Optional<PersonDto>> atomicReference = new AtomicReference<>();

        personRepository.findById(personId).ifPresentOrElse(fPerson -> {
                if (StringUtils.hasText(personDto.getFirstLastName())){
                    fPerson.setFirstLastName(personDto.getFirstLastName());
                }
                if (StringUtils.hasText(personDto.getSecondLastName())){
                    fPerson.setSecondLastName(personDto.getSecondLastName());
                }
                if (StringUtils.hasText(personDto.getGivenName())){
                    fPerson.setGivenName(personDto.getGivenName());
                }
                if (StringUtils.hasText(personDto.getMainAddress())){
                    fPerson.setMainAddress(personDto.getMainAddress());
                }
                if (StringUtils.hasText(personDto.getSecondAddress())){
                    fPerson.setSecondAddress(personDto.getSecondAddress());
                }
                if (personDto.getPhoneNumber() != null){
                    fPerson.setPhoneNumber(personDto.getPhoneNumber());
                }
                if (personDto.getSecondPhoneNumber() != null){
                    fPerson.setSecondPhoneNumber(personDto.getSecondPhoneNumber());
                }
                atomicReference.set(Optional.of(personMapper.personToPersonDto(personRepository.save(fPerson))));
            }, () -> atomicReference.set(Optional.empty()));

        return atomicReference.get();
    }

    @Override
    public Boolean removePersonById(UUID personId) {
        if(personRepository.existsById(personId)){
            personRepository.deleteById(personId);
            return true;
        }
        return false;
    }

    public PageRequest buildPageRequest(Integer pageNumber, Integer pageSize) {
        int queryPageNumber;
        int queryPageSize;

        if (pageNumber != null && pageNumber > CERO) {
            queryPageNumber = pageNumber - ONE;
        } else {
            queryPageNumber = DEFAULT_PAGE;
        }

        if (pageSize == null) {
            queryPageSize = DEFAULT_PAGE_SIZE;
        } else {
            if (pageSize > THOUSAND) {
                queryPageSize = THOUSAND;
            } else {
                queryPageSize = pageSize;
            }
        }

        Sort sort = Sort.by(Sort.Order.asc(FIRST_LAST_NAME));

        return PageRequest.of(queryPageNumber, queryPageSize, sort);
    }
}
