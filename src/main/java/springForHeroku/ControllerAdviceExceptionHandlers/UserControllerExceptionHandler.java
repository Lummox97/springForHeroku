package springForHeroku.ControllerAdviceExceptionHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import springForHeroku.Exceptions.AdminPrivilegeException;

@RestControllerAdvice
public class UserControllerExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(AdminPrivilegeException.class)
  public ResponseEntity<AwesomeException> dontHavePrivileges(AdminPrivilegeException e) {
    return new ResponseEntity<>(new AwesomeException(e.getLocalizedMessage()),
        HttpStatus.PAYMENT_REQUIRED);
  }
}
