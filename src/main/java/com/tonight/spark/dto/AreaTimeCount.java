package com.tonight.spark.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AreaTimeCount {
    private String areaName;
    private Integer time;
    private Integer count;

    public static AreaTimeCount create(String area, List<Integer> durations) {
        return AreaTimeCount.builder()
                .areaName(area)
                .time(durations.stream().reduce(0, Integer::sum))
                .count(durations.size())
                .build();
    }
}
