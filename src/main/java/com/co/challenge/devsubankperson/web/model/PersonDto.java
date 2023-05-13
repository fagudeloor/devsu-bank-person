package com.co.challenge.devsubankperson.web.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PersonDto implements Serializable {

    @FieldNameConstants.Exclude
    static final long serialVersionUID = -7775407104856791565L;

    private UUID id;

    private String documentType;

    private Long documentNumber;

    private String firstLastName;

    private String secondLastName;

    private String givenName;

    private String mainAddress;

    private String secondAddress;

    private Long phoneNumber;

    private Long secondPhoneNumber;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ssZ", shape= JsonFormat.Shape.STRING)
    private OffsetDateTime createdDate;
}
