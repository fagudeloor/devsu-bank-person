package com.co.challenge.devsubankperson.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CustomerDto extends PersonDto implements Serializable {

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private final long serialVersionUID = -7521961435718690669L;

    private UUID id;

    private UUID personId;

    private String userName;

    private String password;

    private String state;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ssZ", shape= JsonFormat.Shape.STRING)
    private OffsetDateTime createdDate;

}
