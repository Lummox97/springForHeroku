package springForHeroku.AOP;

import java.util.List;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import springForHeroku.Exceptions.SubscriptionRequiredException;
import springForHeroku.domains.Task;
import springForHeroku.services.TaskServiceImpl;

@Aspect
@Component
public class  TaskAspect {

  private static final String SECRET = "secret";

  @Pointcut("execution(* springForHeroku.services.TaskServiceImpl.createTask(..))")
  public void checker() {
  }

  @Before("checker()")
  public void limitCheck(JoinPoint joinPoint) throws SubscriptionRequiredException {
    Task task = (Task) joinPoint.getArgs()[0];
    TaskServiceImpl imp = (TaskServiceImpl) joinPoint.getTarget();

    List<Task> tasks;
    tasks = imp.getTasks(task.getUser());

      if (tasks == null || tasks.size() < 10) {
          return;
      }

      if (DigestUtils.md5DigestAsHex(SECRET.getBytes()).equals(task.getUser().getSubscription())) {
          return;
      }

    throw new SubscriptionRequiredException("A subscription required for this operation.");
  }

  @Pointcut("execution(* springForHeroku.services.TaskServiceImpl.attacheFile(..))")
  public void limitchecker() {
  }

  @Before("limitchecker()")
  public void limitationCheckerForUpload(JoinPoint joinPoint) throws SubscriptionRequiredException {
    int id = (int) joinPoint.getArgs()[0];
    TaskServiceImpl service = (TaskServiceImpl) joinPoint.getTarget();
    Task task = service.findById(id);
      if (DigestUtils.md5DigestAsHex(SECRET.getBytes()).equals(task.getUser().getSubscription())) {
          return;
      }
    throw new SubscriptionRequiredException("A subscription required for this operation.");
  }
}
