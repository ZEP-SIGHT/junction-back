package com.tonight.spark.service.impl;

import com.tonight.spark.domain.StayTime;
import com.tonight.spark.dto.ChartArea;
import com.tonight.spark.dto.TimeCount;
import com.tonight.spark.dto.TimeCountDto;
import com.tonight.spark.dto.TotalVisit;
import com.tonight.spark.repository.StayTimeRepository;
import com.tonight.spark.service.ChartService;
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
                .map(StayTime::getAreaName)
                .collect(Collectors.toSet());

        HashMap<String, Map<Integer, Integer>> areaMap = new HashMap<>();

        for (String area : areaSet) {
            areaMap.put(area, new HashMap<>());
        }

        for (StayTime timeByMapHash : stayTimeByMapHash) {

            Map<Integer, Integer> chartsByAreaMap = areaMap.get(timeByMapHash.getAreaName());

            LocalDateTime inTime = timeByMapHash.getInTime();
            int hour = inTime.getHour();
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
    public TotalVisit getTotalVisits(String mapHash) {
        List<StayTime> stayTimeByMapHash = stayTimeRepository.findStayTimeByMapHash(mapHash);
        Map<Integer, Integer> totalVisitMap = new HashMap<>();

        for (StayTime timeByMapHash : stayTimeByMapHash) {
            LocalDateTime inTime = timeByMapHash.getInTime();
            int hour = inTime.getHour();
            if (totalVisitMap.keySet().contains(hour)) {
                totalVisitMap.put(hour, totalVisitMap.get(hour) + 1);
            } else {
                totalVisitMap.put(hour, 1);
            }
        }

        LinkedHashMap<String, TimeCount> hashMap = new LinkedHashMap<>();

        for (int i = 12; i <= 23; i++) {
            String hour = Integer.toString(i);
            String concat = hour.concat(":00");
            TimeCount build = TimeCount.builder()
                    .time(0)
                    .count(totalVisitMap.get(0))
                    .build();
            hashMap.put(concat, build);

        }
        for (int i = 0; i <= 9; i++) {
            String hour = "0".concat(Integer.toString(i));
            String concat = hour.concat(":00");
            TimeCount build = TimeCount.builder()
                    .time(0)
                    .count(totalVisitMap.get(0))
                    .build();
            hashMap.put(concat, build);
        }

        for (int i = 10; i < 12; i++) {
            String hour = Integer.toString(i);
            String concat = hour.concat(":00");
            TimeCount build = TimeCount.builder()
                    .time(0)
                    .count(totalVisitMap.get(0))
                    .build();
            hashMap.put(concat, build);
        }

        int totalCount = 0;
        for (Integer integer : totalVisitMap.keySet()) {
            int count = 0;
            if (totalVisitMap.get(integer) != null) {
                count = totalVisitMap.get(integer);
            }
            TimeCount build = TimeCount.builder()
                    .time(integer)
                    .count(count)
                    .build();
            String hour = Integer.toString(integer);
            if (hour.length() < 2) {
                hour = "0".concat(hour);
            }
            String concat = hour.concat(":00");
            hashMap.put(concat, build);
            totalCount += totalVisitMap.get(integer);
        }

        ArrayList<TimeCountDto> arrayList = new ArrayList<>();
        for (String time : hashMap.keySet()) {
            Integer count = hashMap.get(time).getCount();
            count = count == null ? 0 : count;
            TimeCountDto build = TimeCountDto.builder()
                    .hour(time)
                    .count(count)
                    .build();
            arrayList.add(build);
        }

        return TotalVisit.builder()
                .totalVisit(totalCount)
                .timeCount(arrayList)
                .build();
    }
}
