package springForHeroku.ControllerAdviceExceptionHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import springForHeroku.Exceptions.SubscriptionRequiredException;

@RestControllerAdvice
public class TaskControllerExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(SubscriptionRequiredException.class)
  public ResponseEntity<AwesomeException> dontHaveAccess(SubscriptionRequiredException e) {
    return new ResponseEntity<>(new AwesomeException(e.getLocalizedMessage()),
        HttpStatus.PAYMENT_REQUIRED);
  }

  @ExceptionHandler(MaxUploadSizeExceededException.class)
  public ResponseEntity<AwesomeException> fileSizeIsHuge(MaxUploadSizeExceededException e) {
    return new ResponseEntity<>(new AwesomeException(e.getMessage()), HttpStatus.BAD_REQUEST);
  }
}
