package com.tonight.spark.service.impl;

import com.tonight.spark.domain.StayTime;
import com.tonight.spark.domain.Visited;
import com.tonight.spark.dto.BaseDataFormat;
import com.tonight.spark.dto.NumberVisitor;
import com.tonight.spark.repository.MapRepository;
import com.tonight.spark.repository.StayTimeRepository;
import com.tonight.spark.repository.VisitedRepository;
import com.tonight.spark.service.AreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@RequiredArgsConstructor
public class AreaServiceImpl implements AreaService {

    // REPO 의존 주입
    private final VisitedRepository visitedRepository;
    private final MapRepository mapRepository;

    private final StayTimeRepository stayTimeRepository;


    @Override
    public NumberVisitor getNumberOfVisitor(String mapHash) {

        int totalCount = 0;

        // 지정한 스페이스를 조회
        List<Visited> visitedByMap = visitedRepository.findVisitedByMapHash(mapHash);
        HashMap<String, Integer> areaMap = new HashMap<>();

        visitedByMap.forEach(visited -> {
            String areaName = visited.getAreaName();
            if (areaMap.containsKey(areaName)) {
                areaMap.put(areaName, areaMap.get(areaName) + 1);
            } else {
                areaMap.put(areaName, 1);
            }}
        );
        for (String areaName : areaMap.keySet()) {
            totalCount += areaMap.get(areaName);
        }
        return NumberVisitor.builder()
                .totalNumber(totalCount)
                .areaData(areaMap)
                .build();
    }

    @Override
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
            int stayTimeSeconds = Integer.parseInt(stayTime.getDuration());
            remainMap.put(stayTime.getAreaName(), integer + stayTimeSeconds); // data.getStayTime()
            totalNum += stayTimeSeconds;


            if (Integer.parseInt(stayTime.getDuration()) <= 5) {
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
