package com.ibx.sportium.domain.model;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class TennisMatchResult extends MatchResult {

    private String teamAGames;

    private Boolean teamAServing;

    private String teamBGames;

    private Boolean teamBServing;

    private TennisScoreboard scoreboard;
}
