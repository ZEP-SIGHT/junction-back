package com.junction.tonight.spark.controller;


import com.junction.tonight.spark.dto.ChartArea;
import com.junction.tonight.spark.dto.NumberVisitor;
import com.junction.tonight.spark.dto.TimeCount;
import com.junction.tonight.spark.service.ChartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.junction.tonight.spark.controller.RestControllerBase.API_PREFIX;

@RestController
@RequestMapping(PageTwoController.URL_PREFIX)
@RequiredArgsConstructor
@CrossOrigin(origins = {"https://tonightspark-frontend.pages.dev", "http://localhost:3000"})
public class PageTwoController {

    static final String URL_PREFIX = API_PREFIX + "/page2";
    private final ChartService chartService;

    @GetMapping("/chart-area/{mapHash}")
    public ResponseEntity<List<ChartArea>> getChart(@PathVariable String mapHash) {
        List<ChartArea> chartsArea = chartService.getChartsArea(mapHash);
        return ResponseEntity.ok().body(chartsArea);
    }

    @GetMapping("/total-visit/{mapHash}")
    public ResponseEntity<List<TimeCount>> getTotalVisits(@PathVariable String mapHash) {
        List<TimeCount> chartsArea = chartService.getTotalVisits(mapHash);
        return ResponseEntity.ok().body(chartsArea);
    }


}
