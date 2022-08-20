package com.junction.tonight.spark.controller;


import com.junction.tonight.spark.dto.BaseDataFormat;
import com.junction.tonight.spark.dto.NumberVisitor;
import com.junction.tonight.spark.service.AreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

import static com.junction.tonight.spark.controller.RestControllerBase.API_PREFIX;

@RestController
@RequestMapping(DataCollectController.URL_PREFIX)
@RequiredArgsConstructor
public class AreaController {

    static final String URL_PREFIX = API_PREFIX + "/page3";

    private final AreaService service;


    @GetMapping("/area-user/{mapHash}")
    public ResponseEntity<NumberVisitor> getAreaUser(@PathVariable String mapHash) {
        NumberVisitor visitor = service.getNumberOfVisitor(mapHash);
        return ResponseEntity.ok().body(visitor);
    }

    @GetMapping("/remain-time/{mapHash}")
    public ResponseEntity<BaseDataFormat> getRemainTime(@PathVariable String mapHash) {
        BaseDataFormat remainTime = service.getRemainTime(mapHash);
        return ResponseEntity.ok().body(remainTime);
    }

//    @GetMapping("/bounce-rate/{mapHash}")
//    public ResponseEntity<BaseDataFormat> getRemainTime(@PathVariable String mapHash) {
//        BaseDataFormat remainTime = service.getRemainTime(mapHash);
//        return ResponseEntity.ok().body(remainTime);
//    }
}
