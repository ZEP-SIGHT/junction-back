package com.tonight.spark.dto;

import com.tonight.spark.domain.Visited;
import lombok.Builder;
import lombok.Getter;
import java.util.List;
import java.util.Map;

@Getter
@Builder
public class AreaData {

    private String areaName;
    private Integer areaCount;

    public static AreaData create(Map.Entry<String, List<Visited>> entry) {
        return AreaData.builder()
                .areaName(entry.getKey())
                .areaCount(entry.getValue().size())
                .build();
    }

    @Override
    public String toString() {
        return "AreaData{" +
                "areaName='" + areaName + '\'' +
                ", areaCount=" + areaCount +
                '}';
    }

}
