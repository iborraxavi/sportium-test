package com.ibx.sportium.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
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
