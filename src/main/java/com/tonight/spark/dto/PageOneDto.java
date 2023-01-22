package com.tonight.spark.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PageOneDto {
    public String authority;
    public List<AreaTimeCount> areas;

    public static PageOneDto create(String auth, List<AreaTimeCount> areas) {
        return PageOneDto.builder()
                .authority(auth)
                .areas(areas)
                .build();
    }
}
