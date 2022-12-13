package com.tonight.spark.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

import static com.tonight.spark.dto.TimeCount.getTimeCounts;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChartArea {

    public String areaName;

    public List<TimeCount> timeCountList;

    public ChartArea(Map.Entry<String, Map<Integer, Long>> entry) {
        this.areaName = entry.getKey();
        this.timeCountList = getTimeCounts(entry.getValue());
    }

}
