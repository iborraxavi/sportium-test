package com.ibx.sportium.domain.service;

import java.util.regex.Pattern;

import com.ibx.sportium.domain.model.MatchResult;
import com.ibx.sportium.domain.model.exception.IncorrectTypeException;
import org.springframework.stereotype.Service;

@Service
public class MatchResultService {

  private static final Pattern REGEX_FOOTBALL = Pattern.compile("(.*) (\\d+)-(\\d+) (.*)");

  private static final Pattern REGEX_TENNIS = Pattern.compile("(.*) \\((\\d+)\\) (\\d+) (\\d+)-(.*) (\\d+) \\((\\d+)\\) (.*)");

  private static final Pattern REGEX_AMERICAN_FOOTBALL = Pattern.compile("(.*) (\\d+)-(\\d+) (.*) (1st|2nd|3rd|4th) (.*)");

  private final MatchResultMapperService matchResultMapperService;

  public MatchResultService(final MatchResultMapperService matchResultMapperService) {
    this.matchResultMapperService = matchResultMapperService;
  }

  public MatchResult mapMatchResultByType(final String matchResult) {
    if (REGEX_AMERICAN_FOOTBALL.matcher(matchResult).matches()) {
      return matchResultMapperService.mapAmericanFootballMatchResult(REGEX_AMERICAN_FOOTBALL, matchResult);
    } else if (REGEX_FOOTBALL.matcher(matchResult).matches()) {
      return matchResultMapperService.mapFootballMatchResult(REGEX_FOOTBALL, matchResult);
    } else if (REGEX_TENNIS.matcher(matchResult).matches()) {
      return matchResultMapperService.mapTennisMatchResult(REGEX_TENNIS, matchResult);
    }

    throw new IncorrectTypeException("Match result not found");
  }
}
