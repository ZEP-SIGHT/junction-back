package com.tonight.spark.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PageOneDtoRefactoring {
    public String authority;
    public List<AreaTimeCount> areas;

    public static PageOneDtoRefactoring create(String auth, List<AreaTimeCount> areas) {
        return PageOneDtoRefactoring.builder()
                .authority(auth)
                .areas(areas)
                .build();
    }
}
