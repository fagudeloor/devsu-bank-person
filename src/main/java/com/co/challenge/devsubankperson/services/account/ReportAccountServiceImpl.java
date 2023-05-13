package com.co.challenge.devsubankperson.services.account;

import com.co.challenge.devsubankperson.web.model.ReportDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@ConfigurationProperties(prefix = "bank.report", ignoreUnknownFields = true)
@Component
public class ReportAccountServiceImpl implements ReportAccountService {

    public static final String INVENTORY_PATH = "/api/v1/report?userId={userId}&from={fromDate}&to={toDate}";
    private final RestTemplate restTemplate;

    private String bankServiceHost;

    public void setBankServiceHost(String bankServiceHost) {
        this.bankServiceHost = bankServiceHost;
    }

    public ReportAccountServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public List<ReportDto> getMovementsByDates(UUID userId, Date fromDate, Date toDate, Integer pageNumber, Integer pageSize) {
        log.debug("Calling Bank account report: " + bankServiceHost + INVENTORY_PATH);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity requestEntity = new HttpEntity<>(headers);

        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        Map<String, String> params = new HashMap<>();
        params.put("userId", String.valueOf(userId));
        params.put("fromDate", simpleDateFormat.format(fromDate));
        params.put("toDate", simpleDateFormat.format(toDate));
        params.put("pageNumber", pageNumber==null?null:pageNumber.toString());
        params.put("pageSize", pageSize==null?null:pageSize.toString());

        ResponseEntity<List<ReportDto>> responseEntity = restTemplate
                .exchange(bankServiceHost + INVENTORY_PATH, HttpMethod.GET, requestEntity,
                        new ParameterizedTypeReference<List<ReportDto>>(){}, params);

        log.debug("Returning Bank account report!");
        return responseEntity.getBody().stream().collect(Collectors.toList());
    }
}
