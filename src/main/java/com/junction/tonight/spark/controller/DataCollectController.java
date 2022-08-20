package com.junction.tonight.spark.controller;

import com.junction.tonight.spark.domain.StayTime;
import com.junction.tonight.spark.domain.Visited;
import com.junction.tonight.spark.dto.MapHashResponse;
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
//@CrossOrigin(origins = {"https://tonightspark-frontend.pages.dev", "http://localhost:3000"})
public class DataCollectController extends RestControllerBase{
    static final String URL_PREFIX = API_PREFIX + "/collect";
    static final String VISITED = "/visit";
    static final String LEAVE = "/leave";

    static final String MAP_HASH = "/{mapHash}";
    static final String DESIGNATED_AREA_NAME = "/{areaName}";
    static final String V_PLAYER_ID = "/{vPlayerId}";
    static final String v_PLAYER_AUTH = "/{vPlayerAuth}";

    private final MapService mapService;

    private final CollectService collectService;


    @RequestMapping(
            value = VISITED,
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public ResponseEntity<Visited> createVisited(@RequestBody Visited visited) {

        Visited v = collectService.visitArea(visited);

        return ResponseEntity.ok().body(v);
    }


    /*@RequestMapping(
            value = MAP_HASH + DESIGNATED_AREA_NAME,
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public ResponseEntity<Visited> createVisited(@RequestBody Visited visited) {

        Visited v = collectService.visitArea(visited);

        return ResponseEntity.ok().body(v);
    }
*/
    @PostMapping("/")
    public ResponseEntity<MapHashResponse> getUrl(@RequestBody MapInfo url) {
        String info = mapService.saveUrlInfo(url);
        MapHashResponse response = MapHashResponse.builder()
                .mapHash(info)
                .build();
        return ResponseEntity.ok().body(response);
        // 여기서 parsing 한 값을 던져줘야 browser 에서 계속 갖고 있는다 아마도.
    }

    @RequestMapping(
            value = LEAVE,
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public ResponseEntity<StayTime> createStayTime(@RequestBody StayTime stayTime) {

        StayTime s = collectService.leaveArea(stayTime);

        return ResponseEntity.ok().body(s);
    }
}
