package com.co.challenge.devsubankperson.services.mappers;

import com.co.challenge.devsubankperson.repositories.domain.Customer;
import com.co.challenge.devsubankperson.services.PersonService;
import com.co.challenge.devsubankperson.web.model.CustomerDto;
import com.co.challenge.devsubankperson.web.model.PersonDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static com.co.challenge.devsubankperson.utils.ErrorMessages.USER_NOT_FOUND;

@Slf4j
public abstract class CustomerMapperDecorator implements CustomerMapper {

    private PersonService personService;

    private CustomerMapper customerMapper;

    @Autowired
    public void setPersonService(PersonService personService){
        this.personService = personService;
    }

    @Autowired
    public void setCustomerMapper(CustomerMapper customerMapper){
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerDto customerToCustomerDto(Customer customer){
        CustomerDto customerDto = customerMapper.customerToCustomerDto(customer);
        Optional<PersonDto> oPersonById = personService.getPersonById(customer.getPersonId());

        oPersonById.ifPresentOrElse(p -> {
            customerDto.setGivenName(p.getGivenName());
            customerDto.setPersonId(p.getId());
            customerDto.setDocumentNumber(p.getDocumentNumber());
            customerDto.setDocumentType(p.getDocumentType());
            customerDto.setFirstLastName(p.getFirstLastName());
            customerDto.setSecondLastName(p.getSecondLastName());
            customerDto.setMainAddress(p.getMainAddress());
            customerDto.setSecondAddress(p.getSecondAddress());
            customerDto.setPhoneNumber(p.getPhoneNumber());
            customerDto.setSecondPhoneNumber(p.getSecondPhoneNumber());
        }, () ->{
            log.error(USER_NOT_FOUND);
        });

        return customerDto;
    }

}
