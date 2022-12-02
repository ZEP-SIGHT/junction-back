package com.tonight.spark.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tonight.spark.domain.StayTime;
import com.tonight.spark.domain.Visited;
import com.tonight.spark.dto.AreaTimeCount;
import com.tonight.spark.dto.PageOneDto;
import com.tonight.spark.dto.PageOneDtoRefactoring;
import com.tonight.spark.dto.TimeCount;
import com.tonight.spark.dto.page1.AuthType;
import com.tonight.spark.repository.StayTimeRepository;
import com.tonight.spark.repository.VisitedRepository;
import com.tonight.spark.service.Page1Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Slf4j
public class Page1ServiceImpl implements Page1Service {

    private final VisitedRepository visitedRepository;

    private final StayTimeRepository stayTimeRepository;

    @Override
    public List<PageOneDtoRefactoring> getDataForAuthRefactoring(String mapHash) throws JsonProcessingException {

        List<StayTime> stayTimes = stayTimeRepository.findStayTimeByMapHash(mapHash);
        Map<String, Map<String, List<Integer>>> nearResult = stayTimes.stream().collect(
                groupingBy(StayTime::getPlayerAuth,
                        groupingBy(StayTime::getAreaName, mapping(StayTime::getDuration, toList()))
                )
        );

        return nearResult.entrySet().stream()
                .map(data -> PageOneDtoRefactoring.create(data.getKey(), getAreaTimeCounts(data.getValue())))
                .collect(toList());
    }

    private static List<AreaTimeCount> getAreaTimeCounts(Map<String, List<Integer>> byAuth) {

        return byAuth.entrySet()
                .stream()
                .map(area -> AreaTimeCount.create(area.getKey(), area.getValue()))
                .collect(toList());
    }

    @Override
    public List<PageOneDto> getDataForAuth(String mapHash) throws JsonProcessingException {

        List<PageOneDto> finalResult = new ArrayList<>();
        List<StayTime> stayTimeByMapHash = stayTimeRepository.findStayTimeByMapHash(mapHash);
        List<Visited> VisitByMapHash = visitedRepository.findVisitedByMapHash(mapHash);

        Set<String> areaSet = stayTimeByMapHash.stream().map(data -> data.getAreaName()).collect(Collectors.toSet());

        AuthType[] values = AuthType.values();
        HashMap<AuthType, List<StayTime>> authorityMap = new HashMap<>();

        for (AuthType value : values) {
            authorityMap.put(value, new ArrayList<>());
        }
        for (AuthType authType : authorityMap.keySet()) {
            List<StayTime> collect = stayTimeByMapHash.stream().filter(data -> data.getPlayerAuth().equals(authType.getCode())).collect(Collectors.toList());
            authorityMap.put(authType, collect);
        }

        System.out.println(authorityMap);

        for (AuthType authType : authorityMap.keySet()) { // 6
            log.info("area : {}", areaSet.size());
            HashMap<String, List<TimeCount>> areaHashMap = new HashMap<>();
            areaSet.stream().forEach(area -> areaHashMap.put(area, new ArrayList<>()));

            log.info("area : {} {}", areaHashMap.size(), areaHashMap);
            for (String area : areaSet) { // 9
                List<StayTime> collect = stayTimeByMapHash.stream().filter(stayTime -> stayTime.getAreaName().equals(area))
                        .filter(data -> data.getPlayerAuth().equals(authType.getCode()))
                        .collect(Collectors.toList());
                List<Visited> conditionVisited = VisitByMapHash.stream().filter(visited -> visited.getAreaName().equals(area))
                        .filter(data -> data.getPlayerAuth().equals(authType.getCode()))
                        .collect(Collectors.toList());

                int totalStayTime = 0;
                for (StayTime data : collect) {
                    totalStayTime += (data.getDuration());
                }
                log.info("visit, stytime : {}", conditionVisited.size() + "," + totalStayTime);
                areaHashMap.get(area).add(new TimeCount(conditionVisited.size(),totalStayTime));
            }
            System.out.println(areaHashMap);
            PageOneDto build = PageOneDto.builder()
                    .authorityName(authType.getCode())
                    .mapData(areaHashMap)
                    .build();
            finalResult.add(build);
        }
        return finalResult;
    }

    @Override
    public void test() {
        log.info("test : {}",stayTimeRepository.getStayTimeSumForGroup("NONE", "3001", "DKMaPm"));
    }
}
