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

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;


@Service
@RequiredArgsConstructor
public class ChartServiceImpl implements ChartService {

    private final StayTimeRepository stayTimeRepository;

    @Override
    public List<ChartArea> getChartsArea(String mapHash) {


        List<StayTime> stayTimeByMapHash = stayTimeRepository.findStayTimeByMapHash(mapHash);

        Map<String, Map<Integer, Long>> result = stayTimeByMapHash.stream()
                .collect(groupingBy(StayTime::getAreaName,
                        groupingBy(data -> data.getInTime().getHour(), counting())));

        return result.entrySet()
                .stream()
                .map(ChartArea::new)
                .collect(Collectors.toList());
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
