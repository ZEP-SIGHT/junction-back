package com.junction.tonight.spark.service.impl;

import com.junction.tonight.spark.domain.StayTime;
import com.junction.tonight.spark.dto.ChartArea;
import com.junction.tonight.spark.dto.TimeCount;
import com.junction.tonight.spark.repository.StayTimeRepository;
import com.junction.tonight.spark.service.ChartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ChartServiceImpl implements ChartService {

    private final StayTimeRepository stayTimeRepository;

    @Override
    public List<ChartArea> getChartsArea(String mapHash) {


        List<StayTime> stayTimeByMapHash = stayTimeRepository.findStayTimeByMapHash(mapHash);

        Set<String> areaSet = stayTimeByMapHash.stream()
                .map(StayTime::getDesignatedAreaName)
                .collect(Collectors.toSet());

        HashMap<String, Map<Integer, Integer>> areaMap = new HashMap<>();

        for (String area : areaSet) {
            areaMap.put(area, new HashMap<>());
        }

        for (StayTime timeByMapHash : stayTimeByMapHash) {

            Map<Integer, Integer> chartsByAreaMap = areaMap.get(timeByMapHash.getDesignatedAreaName());
            Map<Integer, Integer> totalVisitMap = new HashMap<>();

            LocalDateTime inTime = timeByMapHash.getInTime();
            int hour = inTime.getHour();
            if (totalVisitMap.keySet().contains(hour)) {
                totalVisitMap.put(hour, chartsByAreaMap.get(hour) + 1);
            } else {
                chartsByAreaMap.put(hour, 1);
            }


            if (hour % 2 != 0) {
                hour -= 1;
            }
            if (chartsByAreaMap.keySet().contains(hour)) {
                chartsByAreaMap.put(hour, chartsByAreaMap.get(hour) + 1);
            } else {
                chartsByAreaMap.put(hour, 1);
            }
        }

        List<ChartArea> chartAreaList = new ArrayList<>();
        for (String areaName : areaMap.keySet()) {
            ArrayList<TimeCount> timeCounts = new ArrayList<>();
            Map<Integer, Integer> map = areaMap.get(areaName);
            for (Integer integer : map.keySet()) {
                TimeCount build = TimeCount.builder()
                        .time(integer)
                        .count(map.get(integer))
                        .build();
                timeCounts.add(build);
            }

            ChartArea build = ChartArea.builder()
                    .areaName(areaName)
                    .timeCountList(timeCounts)
                    .build();
            chartAreaList.add(build);
        }

        return chartAreaList;
    }


    @Override
    public List<TimeCount> getTotalVisits(String mapHash) {
//        List<StayTime> stayTimeByMapHash = stayTimeRepository.findStayTimeByMapHash(mapHash);

        return null;
    }
}