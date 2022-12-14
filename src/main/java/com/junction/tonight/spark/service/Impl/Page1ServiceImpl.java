package com.junction.tonight.spark.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.junction.tonight.spark.common.ObjectConverter;
import com.junction.tonight.spark.domain.StayTime;
import com.junction.tonight.spark.domain.Visited;
import com.junction.tonight.spark.dto.PageOneDto;
import com.junction.tonight.spark.dto.TimeCount;
import com.junction.tonight.spark.dto.page1.AuthType;
import com.junction.tonight.spark.dto.page1.StatisForAuth;
import com.junction.tonight.spark.dto.page1.TestInterface;
import com.junction.tonight.spark.repository.StayTimeRepository;
import com.junction.tonight.spark.repository.VisitedRepository;
import com.junction.tonight.spark.service.Page1Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class Page1ServiceImpl implements Page1Service {

    private final VisitedRepository visitedRepository;

    private final StayTimeRepository stayTimeRepository;

    @Override
    public List<PageOneDto> getDataForAuth(String mapHash) throws JsonProcessingException {

        List<PageOneDto> finalResult = new ArrayList<>();
        List<StayTime> stayTimeByMapHash = stayTimeRepository.findStayTimeByMapHash(mapHash);
        List<Visited> VisitByMapHash = visitedRepository.findVisitedByMapHash(mapHash);

        Set<String> areaSet = stayTimeByMapHash.stream().map(data -> data.getDesignatedAreaName()).collect(Collectors.toSet());

        AuthType[] values = AuthType.values();
        HashMap<AuthType, List<StayTime>> authorityMap = new HashMap<>();

        for (AuthType value : values) {
            authorityMap.put(value, new ArrayList<>());
        }
        for (AuthType authType : authorityMap.keySet()) {
            List<StayTime> collect = stayTimeByMapHash.stream().filter(data -> data.getVPlayerAuth().equals(authType.getCode())).collect(Collectors.toList());
            authorityMap.put(authType, collect);
        }

        System.out.println(authorityMap);

        for (AuthType authType : authorityMap.keySet()) { // 6
            log.info("area : {}", areaSet.size());
            HashMap<String, List<TimeCount>> areaHashMap = new HashMap<>();
            areaSet.stream().forEach(area -> areaHashMap.put(area, new ArrayList<>()));

            log.info("area : {} {}", areaHashMap.size(), areaHashMap);
            for (String area : areaSet) { // 9
                List<StayTime> collect = stayTimeByMapHash.stream().filter(stayTime -> stayTime.getDesignatedAreaName().equals(area))
                        .filter(data -> data.getVPlayerAuth().equals(authType.getCode()))
                        .collect(Collectors.toList());
                List<Visited> conditionVisited = VisitByMapHash.stream().filter(visited -> visited.getDesignatedAreaName().equals(area))
                        .filter(data -> data.getVPlayerAuth().equals(authType.getCode()))
                        .collect(Collectors.toList());

                int totalStayTime = 0;
                for (StayTime data : collect) {
                    totalStayTime += Integer.parseInt(data.getStayTime());
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
