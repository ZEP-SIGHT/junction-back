package com.tonight.spark.controller;

import com.tonight.spark.domain.StayTime;
import com.tonight.spark.domain.Visited;
import com.tonight.spark.dto.MapHashResponse;
import com.tonight.spark.dto.MapInfo;
import com.tonight.spark.dto.VisitDto;
import com.tonight.spark.service.CollectService;
import com.tonight.spark.service.MapService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

/**
 * visit 및 stayTime api
 */
@RestController
@RequestMapping("api/v1/collect")
@RequiredArgsConstructor
@CrossOrigin(origins = {"https://tonightspark-frontend.pages.dev", "http://localhost:3000"})
public class DataCollectController extends RestControllerBase {
    static final String URL_PREFIX = API_PREFIX + "/collect";
    static final String VISITED = "/visit";
    static final String LEAVE = "/leave";

    static final String MAP_HASH = "/{mapHash}";
    static final String DESIGNATED_AREA_NAME = "/{areaName}";
    static final String V_PLAYER_ID = "/{vPlayerId}";
    static final String V_PLAYER_AUTH = "/{vPlayerAuth}";

    static final String IN_TIME = "/{inTime}";
    static final String OUT_TIME = "/{outTime}";

    private final MapService mapService;

    private final CollectService collectService;

    @PostMapping("visit")
    public ResponseEntity<Visited> createVisited(@RequestBody VisitDto visitDto) {

        Visited result = collectService.visitArea(visitDto);
        return ResponseEntity
                .created(URI.create("/visit/" + visitDto.getMapHash()))
                .body(result);
    }

    @ResponseBody
    @PostMapping("leave")
    public ResponseEntity<StayTime> createStayTime(@RequestBody StayTime stayTime) {

        StayTime s = collectService.leaveArea(stayTime);

        return ResponseEntity.ok().body(s);
    }

    @PostMapping("/")
    public ResponseEntity<MapHashResponse> getUrl(@RequestBody MapInfo url) {
        String info = mapService.saveUrlInfo(url);
        MapHashResponse response = MapHashResponse.builder()
                .mapHash(info)
                .build();
        return ResponseEntity.ok().body(response);
    }


}
