package com.co.challenge.devsubankperson.services;


import com.co.challenge.devsubankperson.repositories.CustomerRepository;
import com.co.challenge.devsubankperson.repositories.domain.Customer;
import com.co.challenge.devsubankperson.services.mappers.CustomerMapper;
import com.co.challenge.devsubankperson.web.model.CustomerDto;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public Optional<CustomerDto> getCustomerById(UUID customerId) {
        Optional<Customer> customerFound = customerRepository.findById(customerId);

        return Optional.ofNullable(customerMapper.customerToCustomerDto(customerFound.get()));
    }

    @Override
    public Page<CustomerDto> listCustomer(Integer pageNumber, Integer pageSize) {
            PageRequest pageRequest = buildPageRequest(pageNumber, pageSize);
            Page<Customer> all = customerRepository.findAll(pageRequest);

            return all.map(customerMapper::customerToCustomerDto);
    }

    @Override
    public CustomerDto createNewCustomer(CustomerDto customerDto) {

        log.debug("creating customer: " + customerDto.toString());
        return customerMapper.customerToCustomerDto(
               customerRepository.save(customerMapper.customerDtoToCustomer(customerDto)));
    }

    @Override
    public Optional<CustomerDto> updateCustomerById(UUID customerId, CustomerDto customerDto) {
            AtomicReference<Optional<CustomerDto>> atomicReference = new AtomicReference<>();

            customerRepository.findById(customerId).ifPresentOrElse(foundCustomer -> {
                foundCustomer.setUserName(customerDto.getUserName());
                foundCustomer.setPassword(customerDto.getPassword());
                foundCustomer.setState(customerDto.getState());
                atomicReference.set(Optional.of(customerMapper.customerToCustomerDto(customerRepository.save(foundCustomer))));
            }, () -> atomicReference.set(Optional.empty()));
            return atomicReference.get();
    }

    @Override
    public Optional<CustomerDto> patchCustomerById(UUID customerId, CustomerDto customerDto) {
            AtomicReference<Optional<CustomerDto>> atomicReference = new AtomicReference<>();

            customerRepository.findById(customerId).ifPresentOrElse(fCustomer -> {
                if (StringUtils.hasText(customerDto.getPassword())){
                    fCustomer.setPassword(customerDto.getPassword());
                }
                if (StringUtils.hasText(customerDto.getUserName())){
                    fCustomer.setUserName(customerDto.getUserName());
                }
                if (StringUtils.hasText(customerDto.getState())){
                    fCustomer.setState(customerDto.getState());
                }
                atomicReference.set(Optional.of(customerMapper.customerToCustomerDto(customerRepository.save(fCustomer))));
            }, () -> atomicReference.set(Optional.empty()));

            return atomicReference.get();
    }

    @Override
    public boolean removeCustomerById(UUID customerId) {
        if(customerRepository.existsById(customerId)){
            customerRepository.deleteById(customerId);
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

        Sort sort = Sort.by(Sort.Order.asc(USER_NAME));

        return PageRequest.of(queryPageNumber, queryPageSize, sort);
    }

}
