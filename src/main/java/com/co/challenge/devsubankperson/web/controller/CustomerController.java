package com.co.challenge.devsubankperson.web.controller;

import com.co.challenge.devsubankperson.services.CustomerService;
import com.co.challenge.devsubankperson.web.exceptions.NotFoundException;
import com.co.challenge.devsubankperson.web.model.CustomerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.co.challenge.devsubankperson.utils.Constants.*;
import static com.co.challenge.devsubankperson.utils.ErrorMessages.CUSTOMER_NOT_FOUND;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping(CUSTOMER_PATH_ID)
    public CustomerDto getCustomerById(@PathVariable(CUSTOMER_ID) UUID customerId){
        log.info("Into getMapping id:" + customerId.toString());
        return customerService.getCustomerById(customerId).orElseThrow(NotFoundException::new);
    }

    @GetMapping(CUSTOMER_PATH)
    public Page<CustomerDto> listCustomer(@RequestParam(required = false) Integer pageNumber,
                                       @RequestParam(required = false) Integer pageSize){

        return customerService.listCustomer(pageNumber, pageSize);
    }

    @PostMapping(CUSTOMER_PATH)
    public ResponseEntity createNewCustomer(@RequestBody CustomerDto customerDto){
        log.info("Into postMapping id: " + customerDto.getGivenName());
        CustomerDto createdCustomer = customerService.createNewCustomer(customerDto);

        log.debug("Customer created: " + customerDto.toString());
        HttpHeaders headers = new HttpHeaders();
        headers.add(LOCATION, CUSTOMER_PATH + SLASH + createdCustomer.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping(CUSTOMER_PATH_ID)
    public ResponseEntity updateCustomerById(@PathVariable(CUSTOMER_ID) UUID customerId, @RequestBody CustomerDto customerDto){
        log.info("Into putMapping id: " + customerId.toString());

        if(customerService.updateCustomerById(customerId, customerDto).isEmpty())
            throw new NotFoundException(CUSTOMER_NOT_FOUND);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(CUSTOMER_PATH_ID)
    public ResponseEntity patchCustomer(@PathVariable(CUSTOMER_ID) UUID customerId, @RequestBody CustomerDto customerDto){
        log.info("Into patchMapping id: " + customerId.toString());

        if(customerService.patchCustomerById(customerId, customerDto).isEmpty())
            throw new NotFoundException(CUSTOMER_NOT_FOUND);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(CUSTOMER_PATH_ID)
    public ResponseEntity removeCustomer(@PathVariable(CUSTOMER_ID) UUID customerId){
        log.info("Into deleteMapping id: " + customerId.toString());
        if(!customerService.removeCustomerById(customerId)){
            throw new NotFoundException(CUSTOMER_NOT_FOUND);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
