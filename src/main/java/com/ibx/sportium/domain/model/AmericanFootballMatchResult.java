package com.ibx.sportium.domain.model;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class AmericanFootballMatchResult extends MatchResult {

    private String currentPeriod;
}
