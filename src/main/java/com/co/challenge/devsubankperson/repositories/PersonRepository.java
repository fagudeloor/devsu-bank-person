package com.co.challenge.devsubankperson.repositories;

import com.co.challenge.devsubankperson.repositories.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, UUID> {
}
