package com.tonight.spark.dto.page3;

import lombok.Builder;

@Builder
public class BounceDto {

    public String areaName;
    public String accumulateTime;

    @Override
    public String toString() {
        return "BouncingTime{" +
                "areaName='" + areaName + '\'' +
                ", accumulateTime=" + accumulateTime +
                '}';
    }
}
