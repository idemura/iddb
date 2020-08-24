package id.db;

import org.slf4j.helpers.MessageFormatter;

public final class Debug
{
  private Debug() {}

  public static void check(boolean condition)
  {
    if (!condition) {
      throw new CheckException("check failed");
    }
  }

  public static void check(boolean condition, String description)
  {
    if (!condition) {
      throw new CheckException(description);
    }
  }

  public static void checkEquals(int a, int b)
  {
    if (a != b) {
      throw new CheckException(
          MessageFormatter.format("check failed: expected equal, found {} and {}", a, b)
              .toString());
    }
  }
}
