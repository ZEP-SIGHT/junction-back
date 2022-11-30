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

    public static AreaTimeCount create(String area, List<String> durations) {
        return AreaTimeCount.builder()
                .areaName(area)
                .time(durations.stream().mapToInt(Integer::parseInt).sum())
                .count(durations.size())
                .build();
    }
}
