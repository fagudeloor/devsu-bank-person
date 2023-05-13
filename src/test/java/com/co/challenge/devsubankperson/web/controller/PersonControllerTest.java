package com.co.challenge.devsubankperson.web.controller;

import com.co.challenge.devsubankperson.services.PersonService;
import com.co.challenge.devsubankperson.web.model.PersonDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonController.class)
class PersonControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    PersonService personService;

    @Test
    void getPersonById() throws Exception {
        mockMvc.perform(get("/api/v1/person?personId=" + UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void createNewPerson() throws Exception {
        PersonDto personDto = getValidPersonDto();
        String beerDtoJson = objectMapper.writeValueAsString(personDto);

        given(personService.createNewPerson(any())).willReturn(getValidPersonDto());

        mockMvc.perform(post("/api/v1/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(beerDtoJson))
                .andExpect(status().isCreated());
    }

    @Test
    void updatePersonById() throws Exception {
        given(personService.updatePersonBuyId(any(), any())).willReturn(Optional.ofNullable(getValidPersonDto()));

        PersonDto personDto = getValidPersonDto();
        String personJson = objectMapper.writeValueAsString(personDto);

        mockMvc.perform(put("/api/v1/person/" + UUID.randomUUID().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(personJson))
                .andExpect(status().isNoContent());
    }

    @Test
    void patchPerson() throws Exception {
    }

    @Test
    void removePerson() {
    }

    PersonDto getValidPersonDto(){
        return PersonDto
                .builder()
                .id(UUID.randomUUID())
                .firstLastName("Duarte")
                .secondLastName("Solano")
                .givenName("Luis")
                .createdDate(OffsetDateTime.now())
                .documentNumber(5486645l)
                .documentType("CC")
                .mainAddress("Avenida siempre viva")
                .phoneNumber(54854687l)
                .build();
    }
}