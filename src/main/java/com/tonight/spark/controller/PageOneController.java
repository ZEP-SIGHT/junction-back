package com.tonight.spark.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tonight.spark.dto.PageOneDto;
import com.tonight.spark.service.Page1Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/page1")
@RequiredArgsConstructor
@CrossOrigin(origins = {"https://tonightspark-frontend.pages.dev", "http://localhost:3000"})
public class PageOneController extends RestControllerBase {
    private final Page1Service page1Service;

    @GetMapping("/{mapHash}")
    @ResponseBody
    public ResponseEntity<List<PageOneDto>> getAuthStatistic(@PathVariable String mapHash) throws JsonProcessingException {
        List<PageOneDto> dataForAuth = page1Service.getDataForAuth(mapHash);
        return ResponseEntity.ok().body(dataForAuth);
    }

}
