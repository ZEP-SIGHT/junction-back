package com.junction.tonight.spark.service;

import com.junction.tonight.spark.dto.BaseDataFormat;
import com.junction.tonight.spark.dto.NumberVisitor;
import java.util.Map;

public interface AreaService {

    public NumberVisitor getNumberOfVisitor(String mapHash);

    public Map<String, BaseDataFormat> getRemainTime(String mapHash);

}
