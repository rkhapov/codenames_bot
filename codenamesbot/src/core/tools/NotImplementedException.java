package core.tools;

public class NotImplementedException extends RuntimeException {
  public NotImplementedException(String message) {
    super(message);
  }

  public NotImplementedException() {
    super("Method are not implemented");
  }
}
