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
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;


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
        Map<String, Long> timeCountingMap = stayTimeByMapHash.stream()
                .collect(
                        groupingBy(data -> hourConcat(data.getInTime().getHour()), counting())
                );

        Map<String, Integer> hourMap = IntStream.range(0, 24)
                .mapToObj(ChartServiceImpl::hourConcat)
                .collect(toMap(Function.identity(), i -> 0));

        timeCountingMap.forEach((key, value) ->
                hourMap.put(key, value.intValue())
        );

        List<TimeCountDto> timeCountDto = hourMap.entrySet().stream()
                .map(TimeCountDto::new)
                .collect(toList());

        return TotalVisit.builder()
                .totalVisit(stayTimeByMapHash.size())
                .timeCount(timeCountDto)
                .build();
    }

    private static String hourConcat(int hour) {
        return Integer.toString(hour).concat(":00");
    }
}
