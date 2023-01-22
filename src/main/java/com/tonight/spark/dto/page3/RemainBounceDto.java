package com.tonight.spark.dto.page3;


import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class RemainBounceDto {

    private Integer totalRemainTime;
    private String  totalBounceTimes;
    private List<RemainDto> remainDto;
    private List<BounceDto> bounceDto;

    @Override
    public String toString() {
        return "RemainBounceDto{" +
                "totalRemainTime=" + totalRemainTime +
                ", totalBounceTimes=" + totalBounceTimes +
                ", remainDto=" + remainDto +
                ", bounceDto=" + bounceDto +
                '}';
    }
}
