package com.co.challenge.devsubankperson.repositories.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "", nullable = false)
    private String documentType;

    @Column(name = "", nullable = false)
    private Long documentNumber;

    @Column(name = "", nullable = false)
    private String firstLastName;

    @Column(name = "", nullable = true)
    private String secondLastName;

    @Column(name = "", nullable = false)
    private String givenName;

    @Column(name = "", nullable = false)
    private String mainAddress;

    @Column(name = "", nullable = true)
    private String secondAddress;

    @Column(name = "", nullable = false)
    private Long phoneNumber;

    @Column(name = "", nullable = true)
    private Long secondPhoneNumber;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdDate;

}
