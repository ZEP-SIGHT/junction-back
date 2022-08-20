package com.junction.tonight.spark.service.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.junction.tonight.spark.common.ObjectConverter;
import com.junction.tonight.spark.dto.page1.StatisForAuth;
import com.junction.tonight.spark.dto.page1.TestInterface;
import com.junction.tonight.spark.repository.StayTimeRepository;
import com.junction.tonight.spark.repository.VisitedRepository;
import com.junction.tonight.spark.service.Page1Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class Page1ServiceImpl implements Page1Service {

    private final VisitedRepository visitedRepository;

    private final StayTimeRepository stayTimeRepository;

    @Override
    public StatisForAuth getDataForAuth() throws JsonProcessingException {

        List<TestInterface> tests = stayTimeRepository.findTestInterfaceByNativeQuery();
        log.info("getDataForAuth : {}", ObjectConverter.toJson(tests));
        return null;
    }
}
