package com.junction.tonight.spark.service;

import com.junction.tonight.spark.dto.BaseDataFormat;
import com.junction.tonight.spark.dto.NumberVisitor;
import com.junction.tonight.spark.dto.RemainTime;

public interface AreaService {

    public NumberVisitor getNumberOfVisitor(String mapHash);

    public BaseDataFormat getRemainTime(String mapHash);
}
