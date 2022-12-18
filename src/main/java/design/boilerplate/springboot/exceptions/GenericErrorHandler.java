package design.boilerplate.springboot.exceptions;

import design.boilerplate.springboot.exceptions.response.ApiExceptionResponse;
import design.boilerplate.springboot.utils.ExceptionMessageAccessor;
import feign.FeignException;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GenericErrorHandler extends ResponseEntityExceptionHandler {

  public static final String GENERIC_ERROR = "generic_error";
  @Autowired
  private ExceptionMessageAccessor exceptionMessageAccessor;

  @ExceptionHandler({NoSuchMessageException.class, FeignException.class})
  public ResponseEntity<ApiExceptionResponse> handleExceptions( Exception exception, WebRequest webRequest) {
    var response = new ApiExceptionResponse();
    logger.error(exception.getMessage(), exception);
    response.setMessage(exceptionMessageAccessor.getMessage(null, GENERIC_ERROR));
    response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
    response.setTime(LocalDateTime.now());
    ResponseEntity<ApiExceptionResponse> entity = new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
    return entity;
  }
}
