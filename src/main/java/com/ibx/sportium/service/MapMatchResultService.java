package com.ibx.sportium.service;

import com.ibx.sportium.model.*;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MapMatchResultService {

    private static final Pattern REGEX_FOOTBALL = Pattern.compile("(.*) (\\d+)-(\\d+) (.*)");

    private static final Pattern REGEX_TENNIS = Pattern.compile("(.*) \\((\\d+)\\) (\\d+) (\\d+)-(.*) (\\d+) \\((\\d+)\\) (.*)");

    private static final Pattern REGEX_AMERICAN_FOOTBALL = Pattern.compile("(.*) (\\d+)-(\\d+) (.*) (1st|2nd|3rd|4th) (.*)");

    private static final String CURRENT_PERIOD_FORMAT = "%s %s";

    public MatchResult mapMatchResultByType(final String matchResult) {
        if (REGEX_AMERICAN_FOOTBALL.matcher(matchResult).matches()) {
            return mapAmericanFootballMatchResult(matchResult);
        } else if (REGEX_FOOTBALL.matcher(matchResult).matches()) {
            return mapFootballMatchResult(matchResult);
        } else if (REGEX_TENNIS.matcher(matchResult).matches()) {
            return mapTennisMatchResult(matchResult);
        }

        // TODO XAVI Throw exception
        throw new RuntimeException("Match result not found");
    }

    private MatchResult mapFootballMatchResult(final String matchResult) {
        final Matcher matcher = REGEX_FOOTBALL.matcher(matchResult);
        if (!matcher.matches()) {
            // TODO XAVI Throw exception
            throw new RuntimeException("Football doesn't match");
        }
        return FootballMatchResult.builder()
                .teamAName(matcher.group(1))
                .teamAScore(matcher.group(2))
                .teamBName(matcher.group(4))
                .teamBScore(matcher.group(3))
                .build();
    }

    private MatchResult mapTennisMatchResult(final String matchResult) {
        final Matcher matcher = REGEX_TENNIS.matcher(matchResult);
        if (!matcher.matches()) {
            // TODO XAVI Throw exception
            throw new RuntimeException("Tennis doesn't match");
        }

        final TennisScoreboardItem tennisScoreboardItem = TennisScoreboardItem.builder()
                .title("Sets")
                .teamAScore(matcher.group(2))
                .teamBScore(matcher.group(7))
                .build();

        final TennisScoreboard tennisScoreboard = TennisScoreboard.builder()
                .elements(Collections.singletonList(tennisScoreboardItem))
                .build();

        return TennisMatchResult.builder()
                .teamAName(matcher.group(1).replace("*", ""))
                .teamAScore(matcher.group(4))
                .teamAGames(matcher.group(3))
                .teamAServing(matcher.group(1).startsWith("*") ? true : null)
                .teamBName(matcher.group(8).replace("*", ""))
                .teamBScore(matcher.group(5))
                .teamBGames(matcher.group(6))
                .teamBServing(matcher.group(8).startsWith("*") ? true : null)
                .scoreboard(tennisScoreboard)
                .build();
    }

    private AmericanFootballMatchResult mapAmericanFootballMatchResult(final String matchResult) {
        final Matcher matcher = REGEX_AMERICAN_FOOTBALL.matcher(matchResult);
        if (!matcher.matches()) {
            // TODO XAVI Throw exception
            throw new RuntimeException("American football doesn't match");
        }

        return AmericanFootballMatchResult.builder()
                .teamAName(matcher.group(1))
                .teamAScore(matcher.group(2))
                .teamBName(matcher.group(4))
                .teamBScore(matcher.group(3))
                .currentPeriod(String.format(CURRENT_PERIOD_FORMAT, matcher.group(5), matcher.group(6)))
                .build();
    }
}
