package com.junction.tonight.spark.service.impl;

import com.junction.tonight.spark.domain.Map;
import com.junction.tonight.spark.dto.MapInfo;
import com.junction.tonight.spark.repository.MapRepository;
import com.junction.tonight.spark.service.MapService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Optional;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
@Slf4j
public class MapServiceImpl implements MapService {

    private final MapRepository repository;

    public String saveUrlInfo(MapInfo urlCollect) {

        InputStream response = null;

        String mapName = "";

        try {
            String url = urlCollect.getMapUrl();
            response = new URL(url).openStream();

            Scanner scanner = new Scanner(response);
            String responseBody = scanner.useDelimiter("\\A").next();
            mapName = responseBody
                    .substring(responseBody.indexOf("<title>") + 7, responseBody.indexOf("</title>"))
                    .replace("ZEP - ", "");

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }


        // url 에서 hash 값 추출
        String[] split = urlCollect.getMapUrl().split("play/");
        String mapHash = split[1];

        Map map = new Map(mapHash, mapName, 0);
        repository.save(map);


        return mapHash;
    }

    @Override
    public Map getConcurrentCnt(String mapHash) {
        return repository.findById(mapHash).get();
    }

    @Override
    public Map saveConCurrentCnt(String mapHash, int conCurCnt) {

        Map m = repository.findById(mapHash).get();

        if(m.getVisitedCnt() < conCurCnt) {
            m.setVisitedCnt(conCurCnt);
        }

        return repository.save(m);
    }

}
