package com.co.challenge.devsubankperson.services.mappers;

import com.co.challenge.devsubankperson.repositories.domain.Customer;
import com.co.challenge.devsubankperson.web.model.CustomerDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
@DecoratedWith(CustomerMapperDecorator.class)
public interface CustomerMapper {

    CustomerDto customerToCustomerDto(Customer customer);

    Customer customerDtoToCustomer(CustomerDto customerDto);
}
