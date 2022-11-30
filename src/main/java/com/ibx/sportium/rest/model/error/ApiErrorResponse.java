package com.ibx.sportium.rest.model.error;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ApiErrorResponse {

  private String errorMessage;
}
