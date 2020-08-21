package id.db;

public class CheckException extends RuntimeException {
  public CheckException() {}

  public CheckException(String description) {
    super(description);
  }
}
