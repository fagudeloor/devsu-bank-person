package com.co.challenge.devsubankperson.services.account;

import com.co.challenge.devsubankperson.web.model.ReportDto;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface ReportAccountService {

    List<ReportDto> getMovementsByDates(UUID userId, Date fromDate, Date toDate, Integer pageNumber, Integer pageSize);
}
