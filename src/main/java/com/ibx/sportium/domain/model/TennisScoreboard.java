package com.ibx.sportium.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class TennisScoreboard {

    private List<TennisScoreboardItem> elements;
}
