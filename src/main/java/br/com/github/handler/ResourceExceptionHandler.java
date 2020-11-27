package br.com.github.handler;

import br.com.github.handler.exception.ApiError;
import br.com.github.handler.exception.UserBadRequestError;
import br.com.github.handler.exception.UserNotFoundError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.time.LocalDate;

@ControllerAdvice
public class ResourceExceptionHandler {
  private static final HttpStatus BAD_REQUEST = HttpStatus.BAD_REQUEST;
  private static final HttpStatus NOT_FOUND = HttpStatus.NOT_FOUND;
  private static final HttpStatus UNAUTHORIZED = HttpStatus.UNAUTHORIZED;
  private static final HttpStatus FORBIDDEN = HttpStatus.FORBIDDEN;

  @ExceptionHandler(UserBadRequestError.class)
  public ResponseEntity<ApiError> handlerBadRequestException(UserBadRequestError exception) {
    return ResponseEntity.status(BAD_REQUEST)
        .body(
            createResponseException(exception.getMessage(), BAD_REQUEST.value(), LocalDate.now()));
  }

  @ExceptionHandler(UserNotFoundError.class)
  public ResponseEntity<ApiError> handlerNotFoundException(UserNotFoundError exception) {
    return ResponseEntity.status(UNAUTHORIZED)
        .body(
            createResponseException(exception.getMessage(), UNAUTHORIZED.value(), LocalDate.now()));
  }

  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<ApiError> badCredentialsException(BadCredentialsException exception) {
    return ResponseEntity.status(FORBIDDEN)
        .body(createResponseException(exception.getMessage(), FORBIDDEN.value(), LocalDate.now()));
  }

  @ExceptionHandler(AccessDeniedException.class)
  public ResponseEntity<ApiError> handlerAccessDeniedException(AccessDeniedException exception) {
    return ResponseEntity.status(NOT_FOUND)
        .body(createResponseException(exception.getMessage(), NOT_FOUND.value(), LocalDate.now()));
  }

  private ApiError createResponseException(String message, int statusCode, LocalDate date) {
    return new ApiError(message, statusCode, date);
  }
}
