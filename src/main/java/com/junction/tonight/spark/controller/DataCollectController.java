package com.junction.tonight.spark.controller;

import com.junction.tonight.spark.domain.Visited;
import com.junction.tonight.spark.dto.MapInfo;
import com.junction.tonight.spark.service.CollectService;
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
    static final String URL_PREFIX = API_PREFIX + "/collect";

    private final MapService mapService;

    private final CollectService collectService;

    @RequestMapping(
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public ResponseEntity<Visited> createVisited(@RequestBody Visited visited) {


        collectService.visitArea(visited);

        return null;
    }

    @PostMapping("/")
    public ResponseEntity<String> getUrl(MapInfo url){
        String info = mapService.saveUrlInfo(url);
        return ResponseEntity.ok().body(info);
        // 여기서 parsing 한 값을 던져줘야 browser 에서 계속 갖고 있는다 아마도.


    }
}
