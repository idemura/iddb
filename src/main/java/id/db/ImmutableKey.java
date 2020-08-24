package id.db;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public final class ImmutableKey implements Comparable<ImmutableKey>
{
  private final byte[] key;
  private final long keyHashCode;

  // Constructor is private because we don't make a copy of the key here to ensure immutability.
  // Factory method @of does.
  private ImmutableKey(byte[] key)
  {
    Debug.checkEquals(key.length & 7, 0);
    this.key = key;
    this.keyHashCode = XxHash64.hash64(key, 0);
  }

  public static ImmutableKey of(byte[] key)
  {
    return new ImmutableKey(Arrays.copyOf(key, key.length));
  }

  public static ImmutableKey ofString(String key)
  {
    return new ImmutableKey(key.getBytes(StandardCharsets.UTF_8));
  }

  @Override
  public int hashCode()
  {
    return (int) keyHashCode;
  }

  @Override
  public boolean equals(Object other)
  {
    if (other instanceof ImmutableKey) {
      return Arrays.equals(key, ((ImmutableKey) other).key);
    }
    return false;
  }

  @Override
  public String toString()
  {
    return "Key(\"" + new String(key) + "\")";
  }

  @Override
  public int compareTo(ImmutableKey other)
  {
    return Arrays.compare(key, other.key);
  }

  private static int padToLong(int size)
  {
    return (size + 7) & 7;
  }
}
