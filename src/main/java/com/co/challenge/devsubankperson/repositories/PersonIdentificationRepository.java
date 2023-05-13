package com.co.challenge.devsubankperson.repositories;

import com.co.challenge.devsubankperson.repositories.domain.PersonIdentification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonIdentificationRepository extends JpaRepository<PersonIdentification, Long> {
}
