package com.junction.tonight.spark.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.junction.tonight.spark.dto.page1.StatisForAuth;
import com.junction.tonight.spark.service.Impl.Page1ServiceImpl;
import com.junction.tonight.spark.service.Page1Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(PageOneController.URL_PREFIX)
@RequiredArgsConstructor
@CrossOrigin(origins = {"https://tonightspark-frontend.pages.dev", "http://localhost:3000"})
public class PageOneController extends RestControllerBase{
    static final String URL_PREFIX = API_PREFIX + "/page1";

    private final Page1Service page1Service;

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public ResponseEntity<StatisForAuth> getStatisForAuth() throws JsonProcessingException {


        page1Service.getDataForAuth();

        return ResponseEntity.ok().body(null);
    }

}
