package com.ibx.sportium.rest;

import com.ibx.sportium.domain.model.MatchResult;
import com.ibx.sportium.domain.service.MatchResultService;
import com.ibx.sportium.rest.model.MapMatchResultRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MatchResultController {

  private final MatchResultService matchResultService;

  public MatchResultController(final MatchResultService matchResultService) {
    this.matchResultService = matchResultService;
  }

  @PostMapping("/map-match-result")
  public MatchResult mapMatchResult(@RequestBody MapMatchResultRequest matchResult) {
    return matchResultService.mapMatchResultByType(matchResult.getMatchResult());
  }
}
