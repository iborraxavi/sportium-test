package com.ibx.sportium.model;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class AmericanFootballMatchResult extends MatchResult {

    private String currentPeriod;
}
