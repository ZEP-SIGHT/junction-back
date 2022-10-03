package com.junction.tonight.spark.service;

import com.junction.tonight.spark.domain.StayTime;
import com.junction.tonight.spark.dto.BaseDataFormat;
import com.junction.tonight.spark.dto.NumberVisitor;
import com.junction.tonight.spark.repository.MapRepository;
import com.junction.tonight.spark.repository.StayTimeRepository;
import com.junction.tonight.spark.repository.VisitedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@RequiredArgsConstructor
public class AreaService {

    private final VisitedRepository visitedRepository;
    private final MapRepository mapRepository;

    private final StayTimeRepository stayTimeRepository;


    public NumberVisitor getNumberOfVisitor(String mapHash) {

        // 지정한 스페이스를 조회
        HashMap<String, Integer> areaMap = new HashMap<>();

        visitedRepository.findVisitedByMapHash(mapHash)
                .forEach(visited -> calculateMapName(areaMap, visited.getAreaName()));

        int totalCount = areaMap.values()
                .stream()
                .mapToInt(data -> data)
                .sum();


        return NumberVisitor.builder()
                .totalNumber(totalCount)
                .areaData(areaMap)
                .build();
    }

    private void calculateMapName(HashMap<String, Integer> areaMap, String areaName) {
        Integer mapDefaultValue = areaMap.getOrDefault(areaName, 0);
        if (mapDefaultValue >= 0) {
            areaMap.put(areaName, mapDefaultValue + 1);
        }
    }

    public HashMap<String, BaseDataFormat> getRemainTime(String mapHash) {

        List<StayTime> stayTimes = stayTimeRepository.findStayTimeByMapHash(mapHash);
        Set<String> areaNameSet = new HashSet<>();

        HashMap<String, Integer> remainMap = new HashMap<>();
        HashMap<String, Integer> bounceRateMap = new HashMap<>();

        stayTimes.forEach(visited ->
                areaNameSet.add(visited.getAreaName()));
        areaNameSet.forEach(name -> {
            remainMap.put(name, 0);
            bounceRateMap.put(name, 0);
        });

        Integer totalNum = 0;
        Integer bounceTotalNum = 0;
        for (StayTime stayTime : stayTimes) {
            int integer = remainMap.get(stayTime.getAreaName());
            int stayTimeSeconds = Integer.parseInt(stayTime.getStayTime());
            remainMap.put(stayTime.getAreaName(), integer + stayTimeSeconds); // data.getStayTime()
            totalNum += stayTimeSeconds;


            if (Integer.parseInt(stayTime.getStayTime()) <= 5) {
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


//    @Override
//    public BaseDataFormat getBounceRate(String mapHash) {
//
//        List<StayTime> stayTimes = stayTimeRepository.findStayTimeByMapHash(mapHash);
//
//        HashMap<String, Integer> bounceRateMap = new HashMap<>();
//
//        Set<String> areaNameSet = new HashSet<>();
//
//        stayTimes.forEach(visited ->
//                areaNameSet.add(visited.getDesignatedAreaName()));
//        areaNameSet.forEach(name -> bounceRateMap.put(name, 0));
//
//        int totalNum = 0;
//        for (StayTime stayTime : stayTimes) {
//            Integer integer = bounceRateMap.get(stayTime.getDesignatedAreaName());
//            if (Integer.parseInt(stayTime.getStayTime()) <= 5) {
//                bounceRateMap.put(stayTime.getDesignatedAreaName(), integer + 1); // data.getStayTime()
//                totalNum += 1;
//            }
////            float stayTime = Float.parseFloat(stayTime.getStayTime());
////            bounceRateMap.put(stayTime.getDesignatedAreaName(), integer + stayTimeSeconds); // data.getStayTime()
//        }
//
//        return BaseDataFormat.builder()
//                .totalNumber(totalNum)
//                .areaData(bounceRateMap) // type
//                .build();
//
////        return null;
//    }
}
