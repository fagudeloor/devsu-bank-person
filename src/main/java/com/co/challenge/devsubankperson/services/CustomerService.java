package com.co.challenge.devsubankperson.services;

import com.co.challenge.devsubankperson.web.model.CustomerDto;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

public interface CustomerService {

    Optional<CustomerDto> getCustomerById(UUID personId);

    Page<CustomerDto> listCustomer(Integer pageNumber, Integer pageSize);

    CustomerDto createNewCustomer(CustomerDto customerDto);

    Optional<CustomerDto> updateCustomerById(UUID customerId, CustomerDto customerDto);

    Optional<CustomerDto> patchCustomerById(UUID customerId, CustomerDto customerDto);

    boolean removeCustomerById(UUID customerId);
}
