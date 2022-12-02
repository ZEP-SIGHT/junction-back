package com.tonight.spark.controller;


import com.tonight.spark.dto.NumberVisitor;
import com.tonight.spark.dto.page3.RemainBounceDto;
import com.tonight.spark.service.impl.AreaServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/page3")
@RequiredArgsConstructor
@CrossOrigin(origins = {"https://tonightspark-frontend.pages.dev", "http://localhost:3000"})
public class PageThreeController {

    private final AreaServiceImpl service;

    @GetMapping("/area-user/{mapHash}")
    public ResponseEntity<NumberVisitor> getAreaUser(@PathVariable String mapHash) {
        NumberVisitor visitor = service.getNumberOfVisitor(mapHash);
        return ResponseEntity.ok().body(visitor);
    }

    @GetMapping("/remain-time/{mapHash}")
    public ResponseEntity<RemainBounceDto> getRemainTime(@PathVariable String mapHash) {
        RemainBounceDto remainTime = service.getRemainBounceTime(mapHash);
        return ResponseEntity.ok().body(remainTime);
    }
}
