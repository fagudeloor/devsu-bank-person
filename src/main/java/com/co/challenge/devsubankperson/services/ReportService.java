package com.co.challenge.devsubankperson.services;

import com.co.challenge.devsubankperson.web.model.ReportDto;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface ReportService {

    List<ReportDto> getReportByDates(UUID userId, Date fromDate, Date toDate, Integer pageNumber, Integer pageSize);
}
