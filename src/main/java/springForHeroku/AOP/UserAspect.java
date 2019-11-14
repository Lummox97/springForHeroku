package springForHeroku.AOP;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import springForHeroku.Exceptions.AdminPrivilegeException;
import springForHeroku.Legacy.Inspector;
import springForHeroku.domains.User;

@Aspect
@Component
public class UserAspect {

  @Autowired
  private Inspector inspector;

  @Pointcut("execution(* springForHeroku.services.UserServiceImpl.setUserRole(..))")
  public void checker() {
  }

    /*@Pointcut("execution(* App.services.UserServiceImpl.setUserRole(..))")
    public void checker() {

    }*/

  @Before("checker()")
  public void limitCheck(JoinPoint joinPoint) throws Exception {
    User admin = (User) joinPoint.getArgs()[0];
      if (inspector.inspect(admin.getRole().name())) {
          return;
      }

    throw new AdminPrivilegeException("You are not authorized for this operation, please " +
        "contact your System Administrator or Team Leader!");
  }

}
