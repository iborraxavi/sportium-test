package com.ibx.sportium.rest;

import com.ibx.sportium.model.MatchResult;
import com.ibx.sportium.service.MapMatchResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MapMatchResultController {

    @Autowired
    private MapMatchResultService mapMatchResultService;

    @PostMapping("/map-match-result")
    public MatchResult mapMatchResult(@RequestBody String matchResult) {
        return mapMatchResultService.mapMatchResultByType(matchResult);
    }
}
