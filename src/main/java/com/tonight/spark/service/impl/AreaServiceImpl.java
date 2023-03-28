package com.tonight.spark.service.impl;

import com.tonight.spark.domain.StayTime;
import com.tonight.spark.domain.Visited;
import com.tonight.spark.dto.AreaData;
import com.tonight.spark.dto.NumberVisitor;
import com.tonight.spark.dto.page3.BounceDto;
import com.tonight.spark.dto.page3.RemainBounceDto;
import com.tonight.spark.dto.page3.RemainDto;
import com.tonight.spark.repository.StayTimeRepository;
import com.tonight.spark.repository.VisitedRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.toList;

// page3
@Service
@RequiredArgsConstructor
public class AreaServiceImpl {

    private final VisitedRepository visitedRepository;
    private final StayTimeRepository stayTimeRepository;

    public NumberVisitor getNumberOfVisitor(String mapHash) {
        List<Visited> visits = visitedRepository.findVisitedByMapHash(mapHash);
        Integer totalCount = visits.size();

        Map<String, List<Visited>> collect = visits.stream().collect(groupingBy(Visited::getAreaName));
        List<AreaData> areaData = collect.entrySet()
            .stream()
            .map(AreaData::create)
            .collect(Collectors.toList());

        return NumberVisitor.builder()
            .totalCount(totalCount)
            .areaData(areaData)
            .build();
    }

    public RemainBounceDto getRemainBounceTime(String mapHash) {

        // 이게 몇 만건이면 어떡할거여. -> TEST Code 에서 데이터 증폭시켜서 해보자.
        List<StayTime> stayTimes = stayTimeRepository.findStayTimeByMapHash(mapHash);

        Map<String, Integer> remainTimes = stayTimes.stream()
            .collect(groupingBy(StayTime::getAreaName, summingInt(StayTime::getDuration)));

        stayTimes.stream()
            .collect(groupingBy(StayTime::getAreaName, summingInt(StayTime::getDuration)))
            .entrySet()
            .stream()
            .map(RemainDto::create)
            .collect(toList());;

        List<RemainDto> remainDto = remainTimes.entrySet()
            .stream()
            .map(RemainDto::create)
            .collect(toList());

        Map<String, Integer> bounceTimes = stayTimes.stream()
            .collect(groupingBy(StayTime::getAreaName,
                filtering(data -> data.getDuration() <= 5, summingInt(StayTime::getDuration)))
            );
        DecimalFormat form = new DecimalFormat("#.#");
        List<BounceDto> bounceDto = bounceTimes.entrySet()
            .stream()
            .map(data -> BounceDto.builder()
                .areaName(data.getKey())
                .accumulateTime(form.format((double)data.getValue() / remainTimes.get(data.getKey()) * 100))
                .build())
            .collect(toList());
        Integer totalRemainTimes = remainTimes.values().stream().reduce(0, Integer::sum);
        Integer totalBounceTimes = bounceTimes.values().stream().reduce(0, Integer::sum);
        return RemainBounceDto.builder()
            .totalRemainTime(totalRemainTimes)
            .remainDto(remainDto)
            .totalBounceTimes(form.format((double)totalBounceTimes / totalRemainTimes * 100))
            .bounceDto(bounceDto)
            .build();
    }
}
