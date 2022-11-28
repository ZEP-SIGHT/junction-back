package com.tonight.spark.mapper;

import com.tonight.spark.domain.StayTime;
import com.tonight.spark.dto.StayTimeDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StayTimeMapper {
    StayTimeMapper INSTANCE = Mappers.getMapper(StayTimeMapper.class);

    StayTime dtoToStayTime(StayTimeDto memberDTO);
}
