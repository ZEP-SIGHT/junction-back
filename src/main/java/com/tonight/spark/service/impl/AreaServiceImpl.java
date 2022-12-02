package com.tonight.spark.service.impl;

import com.tonight.spark.domain.StayTime;
import com.tonight.spark.domain.Visited;
import com.tonight.spark.dto.AreaData;
import com.tonight.spark.dto.BaseDataFormat;
import com.tonight.spark.dto.NumberVisitor;
import com.tonight.spark.repository.MapRepository;
import com.tonight.spark.repository.StayTimeRepository;
import com.tonight.spark.repository.VisitedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

// page3
@Service
@RequiredArgsConstructor
public class AreaServiceImpl {

    // REPO 의존 주입
    private final VisitedRepository visitedRepository;
    private final MapRepository mapRepository;

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

    public HashMap<String, BaseDataFormat> getRemainTime(String mapHash) {

        List<StayTime> stayTimes = stayTimeRepository.findStayTimeByMapHash(mapHash);
        Set<String> areaNameSet = new HashSet<>();

        HashMap<String, Integer> remainMap = new HashMap<>();
        HashMap<String, Integer> bounceRateMap = new HashMap<>();

        stayTimes.forEach(visited ->
                areaNameSet.add(visited.getAreaName())
        );

        areaNameSet.forEach(name -> {
            remainMap.put(name, 0);
            bounceRateMap.put(name, 0);
        });

        Integer totalNum = 0;
        Integer bounceTotalNum = 0;
        for (StayTime stayTime : stayTimes) {
            int integer = remainMap.get(stayTime.getAreaName());
            int stayTimeSeconds = stayTime.getDuration();
            remainMap.put(stayTime.getAreaName(), integer + stayTimeSeconds); // data.getStayTime()
            totalNum += stayTimeSeconds;


            if (stayTime.getDuration() <= 5) {
                bounceRateMap.put(stayTime.getAreaName(), integer + 1); // data.getStayTime()
                bounceTotalNum += 1;
            }
        }

        for (String remainKey : remainMap.keySet()) {
            if (remainMap.get(remainKey) != 0) {
                int i = bounceRateMap.get(remainKey) / remainMap.get(remainKey);
                bounceRateMap.put(remainKey, i);
            }
        }

        BaseDataFormat remainTimeFormat = BaseDataFormat.builder().totalNumber(totalNum.doubleValue()).areaData(remainMap).build();
        BaseDataFormat bounceRateFormat = BaseDataFormat.builder().totalNumber(0d).areaData(bounceRateMap).build();
        if (totalNum != 0) {
            double mod = (double) bounceTotalNum / (double) totalNum;
            bounceRateFormat = BaseDataFormat.builder().totalNumber(mod).areaData(bounceRateMap).build();
        }
        HashMap<String, BaseDataFormat> dataFormatMap = new HashMap<>();
        dataFormatMap.put("remain", remainTimeFormat);
        dataFormatMap.put("bounce", bounceRateFormat);
        return dataFormatMap;
    }
}
