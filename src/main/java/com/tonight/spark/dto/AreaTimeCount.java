package com.tonight.spark.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class AreaTimeCount {
    private String areaName;
    private Integer time;
    private Integer count;

    public static AreaTimeCount create(Map.Entry<String, List<Integer>> area) {
        return AreaTimeCount.builder()
                .areaName(area.getKey())
                .time(area.getValue().stream().reduce(0, Integer::sum))
                .count(area.getValue().size())
                .build();
    }
}
