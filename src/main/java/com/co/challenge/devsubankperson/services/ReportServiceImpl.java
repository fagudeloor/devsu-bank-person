package com.co.challenge.devsubankperson.services;

import com.co.challenge.devsubankperson.repositories.PersonRepository;
import com.co.challenge.devsubankperson.repositories.domain.Person;
import com.co.challenge.devsubankperson.services.account.ReportAccountService;
import com.co.challenge.devsubankperson.web.model.ReportDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReportServiceImpl implements ReportService {

    private final PersonRepository personRepository;

    private final ReportAccountService reportAccountService;

    @Override
    public List<ReportDto> getReportByDates(UUID userId, Date fromDate, Date toDate, Integer pageNumber, Integer pageSize) {
        log.debug("Request Report!!!");

        Optional<Person> person = personRepository.findById(userId);
        List<ReportDto> reportDtoList = reportAccountService
                .getMovementsByDates(userId, fromDate, toDate, pageNumber, pageSize);

        reportDtoList.forEach(r -> r.setCustomerName(person.get().getGivenName() + " " +
                person.get().getFirstLastName() + " " + person.get().getSecondLastName()));

        return reportDtoList;
    }
}
