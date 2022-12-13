package com.tonight.spark.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimeCountDto {
    public String hour;
    public Integer count;

    public TimeCountDto(Map.Entry<String, Integer> entry) {
        this.hour = entry.getKey();
        this.count = entry.getValue();
    }

}
