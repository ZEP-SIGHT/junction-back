package com.junction.tonight.spark.service;

import com.junction.tonight.spark.dto.BaseDataFormat;
import com.junction.tonight.spark.dto.NumberVisitor;

public interface AreaService {

    public NumberVisitor getNumberOfVisitor(String mapHash);

    public BaseDataFormat getRemainTime(String mapHash);

    public BaseDataFormat getBounceRate(String mapHash);
}
