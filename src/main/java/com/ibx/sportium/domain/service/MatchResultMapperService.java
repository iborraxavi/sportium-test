package com.ibx.sportium.domain.service;

import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ibx.sportium.domain.model.AmericanFootballMatchResult;
import com.ibx.sportium.domain.model.FootballMatchResult;
import com.ibx.sportium.domain.model.MatchResult;
import com.ibx.sportium.domain.model.TennisMatchResult;
import com.ibx.sportium.domain.model.TennisScoreboard;
import com.ibx.sportium.domain.model.TennisScoreboardItem;
import com.ibx.sportium.domain.model.exception.IncorrectFormatException;
import org.springframework.stereotype.Service;

@Service
public class MatchResultMapperService {

  private static final String CURRENT_PERIOD_FORMAT = "%s %s";

  private static final String TENNIS_SCOREBOARD_ITEM_TITLE = "Sets";

  public MatchResult mapFootballMatchResult(final Pattern pattern, final String matchResult) {
    final Matcher matcher = pattern.matcher(matchResult);
    if (!matcher.matches()) {
      throw new IncorrectFormatException("Football doesn't match");
    }

    return FootballMatchResult.builder()
        .teamAName(matcher.group(1))
        .teamAScore(matcher.group(2))
        .teamBName(matcher.group(4))
        .teamBScore(matcher.group(3))
        .build();
  }

  public MatchResult mapTennisMatchResult(final Pattern pattern, final String matchResult) {
    final Matcher matcher = pattern.matcher(matchResult);
    if (!matcher.matches()) {
      throw new IncorrectFormatException("Tennis doesn't match");
    }

    return TennisMatchResult.builder()
        .teamAName(matcher.group(1).replace("*", ""))
        .teamAScore(matcher.group(4))
        .teamAGames(matcher.group(3))
        .teamAServing(matcher.group(1).startsWith("*"))
        .teamBName(matcher.group(8).replace("*", ""))
        .teamBScore(matcher.group(5))
        .teamBGames(matcher.group(6))
        .teamBServing(matcher.group(8).startsWith("*"))
        .scoreboard(mapTennisScoreboard(matcher))
        .build();
  }

  public AmericanFootballMatchResult mapAmericanFootballMatchResult(final Pattern pattern, final String matchResult) {
    final Matcher matcher = pattern.matcher(matchResult);
    if (!matcher.matches()) {
      throw new IncorrectFormatException("American football doesn't match");
    }

    return AmericanFootballMatchResult.builder()
        .teamAName(matcher.group(1))
        .teamAScore(matcher.group(2))
        .teamBName(matcher.group(4))
        .teamBScore(matcher.group(3))
        .currentPeriod(String.format(CURRENT_PERIOD_FORMAT, matcher.group(5), matcher.group(6)))
        .build();
  }

  private TennisScoreboard mapTennisScoreboard(final Matcher matcher) {
    return TennisScoreboard.builder()
        .elements(Collections.singletonList(mapTennisScoreboardItem(matcher)))
        .build();
  }

  private TennisScoreboardItem mapTennisScoreboardItem(final Matcher matcher) {
    return TennisScoreboardItem.builder()
        .title(TENNIS_SCOREBOARD_ITEM_TITLE)
        .teamAScore(matcher.group(2))
        .teamBScore(matcher.group(7))
        .build();
  }
}
