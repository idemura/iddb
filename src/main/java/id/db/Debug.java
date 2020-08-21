package id.db;

public final class Debug {
  private Debug() {}

  public static void check(boolean condition) {
    if (!condition) {
      throw new CheckException("check failed");
    }
  }

  public static void check(boolean condition, String description) {
    if (!condition) {
      throw new CheckException(description);
    }
  }
}
