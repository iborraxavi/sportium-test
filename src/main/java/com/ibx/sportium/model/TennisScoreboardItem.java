package com.ibx.sportium.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TennisScoreboardItem {

    private String title;

    private String teamAScore;

    private String teamBScore;
}
