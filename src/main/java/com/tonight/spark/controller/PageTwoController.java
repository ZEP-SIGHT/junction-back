package com.tonight.spark.controller;


import com.tonight.spark.dto.ChartArea;
import com.tonight.spark.dto.TotalVisit;
import com.tonight.spark.service.ChartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static com.tonight.spark.controller.RestControllerBase.API_PREFIX;

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
    public ResponseEntity<TotalVisit> getTotalVisits(@PathVariable String mapHash) {
        TotalVisit chartsArea = chartService.getTotalVisits(mapHash);
        return ResponseEntity.ok().body(chartsArea);
    }
}
