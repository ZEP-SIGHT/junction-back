package com.tonight.spark.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tonight.spark.domain.StayTime;
import com.tonight.spark.dto.AreaTimeCount;
import com.tonight.spark.dto.PageOneDto;
import com.tonight.spark.repository.StayTimeRepository;
import com.tonight.spark.service.Page1Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Slf4j
public class Page1ServiceImpl implements Page1Service {

    private final StayTimeRepository stayTimeRepository;

    public List<PageOneDto> getDataForAuth(String mapHash) throws JsonProcessingException {

        List<StayTime> stayTimes = stayTimeRepository.findStayTimeByMapHash(mapHash);
        Map<String, Map<String, List<Integer>>> nearResult = stayTimes.stream().collect(
                groupingBy(StayTime::getPlayerAuth,
                        groupingBy(StayTime::getAreaName, mapping(StayTime::getDuration, toList()))
                )
        );

        return nearResult.entrySet().stream()
                .map(data -> PageOneDto.create(data.getKey(), getAreaTimeCounts(data.getValue())))
                .collect(toList());
    }

    private static List<AreaTimeCount> getAreaTimeCounts(Map<String, List<Integer>> byAuth) {

        return byAuth.entrySet()
                .stream()
                .map(area -> AreaTimeCount.create(area.getKey(), area.getValue()))
                .collect(toList());
    }

    @Override
    public void test() {
        log.info("test : {}", stayTimeRepository.getStayTimeSumForGroup("NONE", "3001", "DKMaPm"));
    }
}
