package com.tonight.spark.controller;

import com.tonight.spark.domain.StayTime;
import com.tonight.spark.domain.Visited;
import com.tonight.spark.dto.MapHashResponse;
import com.tonight.spark.dto.MapInfo;
import com.tonight.spark.service.CollectService;
import com.tonight.spark.service.MapService;
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
@CrossOrigin(origins = {"https://tonightspark-frontend.pages.dev", "http://localhost:3000"})
public class DataCollectController extends RestControllerBase{
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

    //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
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


    /*@RequestMapping(
            value = MAP_HASH + DESIGNATED_AREA_NAME + V_PLAYER_ID + V_PLAYER_AUTH,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public ResponseEntity<Visited> createVisited(
            @PathVariable String mapHash,
            @PathVariable String areaName,
            @PathVariable String vPlayerId,
            @PathVariable String vPlayerAuth
    ) {

        Visited getV = Visited.builder()
                .mapHash(mapHash)
                .designatedAreaName(areaName)
                .vPlayerId(vPlayerId)
                .vPlayerAuth(vPlayerAuth)
                .build();

        Visited v = collectService.visitArea(getV);

        return ResponseEntity.ok().body(v);
    }

    @RequestMapping(
            value = MAP_HASH + DESIGNATED_AREA_NAME + V_PLAYER_ID + V_PLAYER_AUTH + IN_TIME + OUT_TIME,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public ResponseEntity<StayTime> createStayTime(
            @PathVariable String mapHash,
            @PathVariable String areaName,
            @PathVariable String vPlayerId,
            @PathVariable String vPlayerAuth,
            @PathVariable String inTime,
            @PathVariable String outTime
    ) {

        StayTime getSt = StayTime.builder()
                .mapHash(mapHash)
                .designatedAreaName(areaName)
                .vPlayerId(vPlayerId)
                .vPlayerAuth(vPlayerAuth)
                .inTime(LocalDateTime.parse(inTime, formatter))
                .outTime(LocalDateTime.parse(outTime, formatter))
                .build();

        StayTime s = collectService.leaveArea(getSt);

        return ResponseEntity.ok().body(s);
    }*/


    @PostMapping("/")
    public ResponseEntity<MapHashResponse> getUrl(@RequestBody MapInfo url) {
        String info = mapService.saveUrlInfo(url);
        MapHashResponse response = MapHashResponse.builder()
                .mapHash(info)
                .build();
        return ResponseEntity.ok().body(response);
        // 여기서 parsing 한 값을 던져줘야 browser 에서 계속 갖고 있는다 아마도.
    }


}
