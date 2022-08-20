package com.junction.tonight.spark.controller;

import com.junction.tonight.spark.domain.Visited;
import com.junction.tonight.spark.dto.MapInfo;
import com.junction.tonight.spark.service.MapService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *  visit 및 stayTime api
 */
@RestController
@RequestMapping(DataCollectController.URL_PREFIX)
@RequiredArgsConstructor
public class DataCollectController extends RestControllerBase{

    private final MapService mapService;

    static final String URL_PREFIX = API_PREFIX + "/collect";

    @RequestMapping(
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public ResponseEntity<Visited> createVisited() {
        return null;
    }

    @PostMapping("/")
    public void getUrl(MapInfo url){
        mapService.saveUrlInfo(url);
    }
}
