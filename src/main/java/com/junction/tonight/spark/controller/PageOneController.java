package com.junction.tonight.spark.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.junction.tonight.spark.dto.PageOneDto;
import com.junction.tonight.spark.service.Page1Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(PageOneController.URL_PREFIX)
@RequiredArgsConstructor
@CrossOrigin(origins = {"https://tonightspark-frontend.pages.dev", "http://localhost:3000"})
public class PageOneController extends RestControllerBase{
    static final String URL_PREFIX = API_PREFIX + "/page1";

    private final Page1Service page1Service;

    @RequestMapping(
            value = "/{mapHash}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public ResponseEntity<List<PageOneDto>> getStatisForAuth(@PathVariable String mapHash) throws JsonProcessingException {


        List<PageOneDto> dataForAuth = page1Service.getDataForAuth(mapHash);
        //page1Service.test();
        return ResponseEntity.ok().body(dataForAuth);
    }

}
