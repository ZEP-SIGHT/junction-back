package com.tonight.spark.controller;


import com.tonight.spark.dto.BaseDataFormat;
import com.tonight.spark.dto.NumberVisitor;
import com.tonight.spark.service.AreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import static com.tonight.spark.controller.RestControllerBase.API_PREFIX;

@RestController
@RequestMapping(PageThreeController.URL_PREFIX)
@RequiredArgsConstructor
@CrossOrigin(origins = {"https://tonightspark-frontend.pages.dev", "http://localhost:3000"})
public class PageThreeController {

    static final String URL_PREFIX = API_PREFIX + "/page3";

    private final AreaService service;


    @GetMapping("/area-user/{mapHash}")
    public ResponseEntity<NumberVisitor> getAreaUser(@PathVariable String mapHash) {
        NumberVisitor visitor = service.getNumberOfVisitor(mapHash);
        return ResponseEntity.ok().body(visitor);
    }

    @GetMapping("/remain-time/{mapHash}")
    public ResponseEntity<HashMap<String, BaseDataFormat>> getRemainTime(@PathVariable String mapHash) {
        HashMap<String, BaseDataFormat> remainTime = service.getRemainTime(mapHash);
        return ResponseEntity.ok().body(remainTime);
    }
}
