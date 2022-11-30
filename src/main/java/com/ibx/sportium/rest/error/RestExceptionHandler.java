package com.ibx.sportium.rest.error;

import com.ibx.sportium.domain.model.exception.IncorrectFormatException;
import com.ibx.sportium.domain.model.exception.IncorrectTypeException;
import com.ibx.sportium.rest.model.error.ApiErrorResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(IncorrectFormatException.class)
  protected ResponseEntity<ApiErrorResponse> handleIncorrectFormatException(final IncorrectFormatException incorrectFormatException) {
    final ApiErrorResponse apiErrorResponse = ApiErrorResponse.builder()
        .errorMessage(incorrectFormatException.getMessage())
        .build();

    return buildResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY, apiErrorResponse);
  }

  @ExceptionHandler(IncorrectTypeException.class)
  protected ResponseEntity<ApiErrorResponse> handleIncorrectTypeException(final IncorrectTypeException incorrectTypeException) {
    final ApiErrorResponse apiErrorResponse = ApiErrorResponse.builder()
        .errorMessage(incorrectTypeException.getMessage())
        .build();

    return buildResponseEntity(HttpStatus.BAD_REQUEST, apiErrorResponse);
  }

  @ExceptionHandler(Exception.class)
  protected ResponseEntity<ApiErrorResponse> handleException(final Exception exception) {
    final ApiErrorResponse apiErrorResponse = ApiErrorResponse.builder()
        .errorMessage(exception.getMessage())
        .build();

    return buildResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, apiErrorResponse);
  }

  private ResponseEntity<ApiErrorResponse> buildResponseEntity(final HttpStatus httpStatus, final ApiErrorResponse apiErrorResponse) {
    return new ResponseEntity<>(apiErrorResponse, httpStatus);
  }
}
