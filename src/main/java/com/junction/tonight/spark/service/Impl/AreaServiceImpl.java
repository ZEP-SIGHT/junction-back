package com.junction.tonight.spark.service.impl;

import com.junction.tonight.spark.domain.Map;
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

        Map mapByMapHash = mapRepository.findMapByMapHash(mapHash);
        List<Visited> visitedByMap = visitedRepository.findVisitedByMap(mapByMapHash);
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
    public BaseDataFormat getRemainTime(String mapHash) {

        List<StayTime> stayTimes = stayTimeRepository.findStayTimeByMapHash(mapHash);

        HashMap<String, Integer> remainMap = new HashMap<>();

        Set<String> areaNameSet = new HashSet<>();

        stayTimes.forEach(visited ->
                areaNameSet.add(visited.getDesignatedAreaName()));
        areaNameSet.forEach(name -> remainMap.put(name, 0));

        int totalNum = 0;
        for (StayTime stayTime : stayTimes) {
            int integer = remainMap.get(stayTime.getDesignatedAreaName());
            totalNum += integer; // 여기서 시간 (초) 덧셈 로직 필요 TODO : Integer.valueOf() 임시 처리
            remainMap.put(stayTime.getDesignatedAreaName(), integer + 1); // data.getStayTime()
        }
        System.out.println(remainMap);
        return BaseDataFormat.builder().totalNumber(totalNum).areaData(remainMap).build();
    }
}
