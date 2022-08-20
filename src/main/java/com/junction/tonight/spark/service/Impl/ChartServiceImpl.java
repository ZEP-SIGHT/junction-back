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

            Map<Integer, Integer> stringIntegerMap = areaMap.get(timeByMapHash.getDesignatedAreaName());
            LocalDateTime inTime = timeByMapHash.getInTime();
            int hour = inTime.getHour();
//            System.out.println(inTime);
//            System.out.println(hour); // 짝수면 그대로 저장, 홀수면 자기보다 하나 적게 저장.

            if (hour % 2 != 0) {
                hour -= 1;
            }
            if (stringIntegerMap.keySet().contains(hour)) {
                stringIntegerMap.put(hour, stringIntegerMap.get(hour) + 1);
            } else {
                stringIntegerMap.put(hour, 1);
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
}
