package com.junction.tonight.spark.service.impl;

import com.junction.tonight.spark.domain.StayTime;
import com.junction.tonight.spark.domain.Visited;
import com.junction.tonight.spark.dto.BaseDataFormat;
import com.junction.tonight.spark.dto.NumberVisitor;
import com.junction.tonight.spark.repository.MapRepository;
import com.junction.tonight.spark.repository.StayTimeRepository;
import com.junction.tonight.spark.repository.VisitedRepository;
import com.junction.tonight.spark.service.AreaService;
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

        HashMap<String, List<String>> areaMap = new HashMap<>();
        HashMap<String, Integer> finalMap = new HashMap<>();

        List<Visited> visitedByMap = visitedRepository.findVisitedByMapHash(mapHash);
        Set<String> areaNameSet = new HashSet<>();
        visitedByMap.forEach(visited ->
                areaNameSet.add(visited.getDesignatedAreaName()));
        areaNameSet.forEach(name -> areaMap.put(name, new ArrayList<>()));
        visitedByMap
                .forEach(visited -> {
                    List<String> arrays = areaMap.get(visited.getDesignatedAreaName());
                    arrays.add(visited.getVPlayerId());
                });

        int totalCount = 0;
        for (String s : areaMap.keySet()) {
            finalMap.put(s, areaMap.get(s).size());
            totalCount += areaMap.get(s).size();
        }

        return NumberVisitor.builder().totalNumber(totalCount).areaData(finalMap).build();
    }

    @Override
    public HashMap<String, BaseDataFormat> getRemainTime(String mapHash) {

        List<StayTime> stayTimes = stayTimeRepository.findStayTimeByMapHash(mapHash);
        Set<String> areaNameSet = new HashSet<>();

        HashMap<String, Integer> remainMap = new HashMap<>();
        HashMap<String, Integer> bounceRateMap = new HashMap<>();

        stayTimes.forEach(visited ->
                areaNameSet.add(visited.getDesignatedAreaName()));
        areaNameSet.forEach(name -> {
            remainMap.put(name, 0);
            bounceRateMap.put(name, 0);
        });

        int totalNum = 0;
        int bounceTotalNum = 0;
        for (StayTime stayTime : stayTimes) {
            int integer = remainMap.get(stayTime.getDesignatedAreaName());
            int stayTimeSeconds = Integer.parseInt(stayTime.getStayTime());
            remainMap.put(stayTime.getDesignatedAreaName(), integer + stayTimeSeconds); // data.getStayTime()
            totalNum += stayTimeSeconds;


            if (Integer.parseInt(stayTime.getStayTime()) <= 5) {
                bounceRateMap.put(stayTime.getDesignatedAreaName(), integer + 1); // data.getStayTime()
                bounceTotalNum += 1;
            }
        }

        for (String remainKey : remainMap.keySet()) {
            if (remainMap.get(remainKey) != 0) {
                int i = bounceRateMap.get(remainKey) / remainMap.get(remainKey);
                bounceRateMap.put(remainKey, i);

            }
//            int i = bounceRateMap.get(remainKey) / remainMap.get(remainKey);
//            bounceRateMap.put(remainKey, i);
        }

        BaseDataFormat remainTimeFormat = BaseDataFormat.builder().totalNumber(totalNum).areaData(remainMap).build();
        BaseDataFormat bounceRateFormat = BaseDataFormat.builder().totalNumber(bounceTotalNum).areaData(bounceRateMap).build();
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
