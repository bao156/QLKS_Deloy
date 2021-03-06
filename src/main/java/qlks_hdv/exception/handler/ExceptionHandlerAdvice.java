package qlks_hdv.exception.handler;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import qlks_hdv.exception.BadRequestException;
import qlks_hdv.exception.ConflictException;
import qlks_hdv.exception.NotFoundException;
import qlks_hdv.response.ErrorResponse;
import qlks_hdv.util.DateUtils;

@Slf4j
@ControllerAdvice
@RestController
@RequiredArgsConstructor
public class ExceptionHandlerAdvice {

  @ExceptionHandler(ConflictException.class)
  public ResponseEntity<ErrorResponse> handleUsernameAlreadyExistsException(
      ConflictException ex) {
    ErrorResponse errorResponse = ErrorResponse.builder()
        .timestamp(DateUtils.now())
        .status(HttpStatus.CONFLICT.value())
        .errorCode(ex.getMessage())
        .build();
    return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ErrorResponse> handleRoleNotFoundException(
      NotFoundException ex) {
    ErrorResponse errorResponse = ErrorResponse.builder()
        .timestamp(DateUtils.now())
        .status(HttpStatus.NOT_FOUND.value())
        .errorCode(ex.getMessage())
        .build();
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  protected ResponseEntity<Object> handle(MethodArgumentNotValidException ex) {
    FieldError error = ex.getBindingResult().getFieldErrors().stream().findFirst().orElse(null);

    if (error == null) {
      ErrorResponse errorResponse = ErrorResponse.builder()
          .timestamp(DateUtils.now())
          .status(HttpStatus.BAD_REQUEST.value())
          .errorCode("unknown-error")
          .build();
      return ResponseEntity.badRequest().body(errorResponse);
    }

    ErrorResponse errorResponse = ErrorResponse.builder()
        .timestamp(DateUtils.now())
        .status(HttpStatus.BAD_REQUEST.value())
        .errorCode(error.getField() + "-"
            + Objects.requireNonNull(error.getDefaultMessage()).replaceAll("\\s", "-"))
        .build();
    return ResponseEntity.badRequest().body(errorResponse);
  }

  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<ErrorResponse> handleBadRequestException(
      BadRequestException ex) {
    ErrorResponse errorResponse = ErrorResponse.builder()
        .timestamp(DateUtils.now())
        .status(HttpStatus.BAD_REQUEST.value())
        .errorCode(ex.getMessage())
        .build();
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
  }


}
