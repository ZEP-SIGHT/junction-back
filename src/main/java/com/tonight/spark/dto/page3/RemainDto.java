package com.tonight.spark.dto.page3;

import lombok.Builder;

import java.util.Map;


@Builder
public class RemainDto {

    public String areaName;
    public Integer accumulateTime;

    public static RemainDto create(Map.Entry<String, Integer> entry) {
        return RemainDto.builder()
                .areaName(entry.getKey())
                .accumulateTime(entry.getValue())
                .build();
    }

    @Override
    public String toString() {
        return "RemainingTime{" +
                "areaName='" + areaName + '\'' +
                ", accumulateTime=" + accumulateTime +
                '}';
    }
}
