package com.junction.tonight.spark.controller;

import com.junction.tonight.spark.domain.Map;
import com.junction.tonight.spark.domain.Visited;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *  visit 및 stayTime api
 */
@RestController
@RequestMapping(DataCollectController.URL_PREFIX)
public class DataCollectController extends RestControllerBase{

    static final String URL_PREFIX = API_PREFIX + "/collect";

    @RequestMapping(
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public ResponseEntity<Visited> createVisited() {


    }
}
