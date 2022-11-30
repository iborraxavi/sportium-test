package com.ibx.sportium.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MatchResult {

    private String teamAName;

    private String teamAScore;

    private String teamBName;

    private String teamBScore;
}
