package springForHeroku.Exceptions;

public class SubscriptionRequiredException extends Exception {

  public SubscriptionRequiredException(String message) {
    super(message);
  }
}
