package com.tonight.spark.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TimeCount {

    public Integer time;
    public Integer count;

    public TimeCount(Map.Entry<Integer, Integer> data) {
        this.time = data.getKey();
        this.count = data.getValue();
    }

    public static List<TimeCount> getTimeCounts(Map<Integer, Long> hourCount) {
        Map<Integer, Integer> integerIntegerMap = generateEvenHourCount(hourCount);

        return integerIntegerMap.entrySet()
                .stream()
                .map(TimeCount::new)
                .collect(Collectors.toList());
    }

    public static Map<Integer, Integer> generateEvenHourCount(Map<Integer, Long> hourCount) {
        Map<Integer, Integer> chartsByAreaMap = new HashMap<>();
        for (Integer hour : hourCount.keySet()) {
            if (hour % 2 != 0) {
                hour -= 1; // 홀수 시간대는 1을 차감하여, 짝수 시간대로 만들어 버린다. ex) 3 -> 2, 1 -> 0
            }
            if (chartsByAreaMap.containsKey(hour)) {
                chartsByAreaMap.put(hour, chartsByAreaMap.get(hour) + 1);
            } else {
                chartsByAreaMap.put(hour, 1);
            }
        }
        return chartsByAreaMap;
    }


}
