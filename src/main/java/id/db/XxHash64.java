package id.db;

// Assumes key length to be multiple of 8.
public final class XxHash64
{
  private static final long PRIME_1 = 0x9E3779B185EBCA87L; // 0b1001111000110111011110011011000110000101111010111100101010000111
  private static final long PRIME_2 = 0xC2B2AE3D27D4EB4FL; // 0b1100001010110010101011100011110100100111110101001110101101001111
  private static final long PRIME_3 = 0x165667B19E3779F9L; // 0b0001011001010110011001111011000110011110001101110111100111111001
  private static final long PRIME_4 = 0x85EBCA77C2B2AE63L; // 0b1000010111101011110010100111011111000010101100101010111001100011
  private static final long PRIME_5 = 0x27D4EB2F165667C5L; // 0b0010011111010100111010110010111100010110010101100110011111000101

  static long getLong(byte[] key, int i)
  {
    long l = 0;
    l |= (key[i] & 0xff);
    l |= (key[i + 1] & 0xff) << 8;
    l |= (key[i + 2] & 0xff) << 16;
    l |= (key[i + 3] & 0xff) << 24;
    l |= ((long) (key[i + 4] & 0xff)) << 32;
    l |= ((long) (key[i + 5] & 0xff)) << 40;
    l |= ((long) (key[i + 6] & 0xff)) << 48;
    l |= ((long) (key[i + 7] & 0xff)) << 56;
    return l;
  }

  private static long round(long acc, long input)
  {
    return Long.rotateLeft(acc + input * PRIME_2, 31) * PRIME_1;
  }

  private static long merge(long acc, long val)
  {
    return (acc ^ round(0, val)) * PRIME_1 + PRIME_4;
  }

  private static long avalanche(long h64)
  {
    h64 ^= h64 >> 33;
    h64 *= PRIME_2;
    h64 ^= h64 >> 29;
    h64 *= PRIME_3;
    h64 ^= h64 >> 32;
    return h64;
  }

  public static long hash64(byte[] key, long seed)
  {
    final int length = key.length;
    Debug.checkEquals(length & 7, 0);
    int index = 0;
    long h64;
    if (length >= 32) {
      int lb = length - 32;
      long v1 = seed + PRIME_1 + PRIME_2;
      long v2 = seed + PRIME_2;
      long v3 = seed;
      long v4 = seed - PRIME_1;

      do {
        v1 = round(v1, getLong(key, index));
        index += 8;
        v2 = round(v2, getLong(key, index));
        index += 8;
        v3 = round(v3, getLong(key, index));
        index += 8;
        v4 = round(v4, getLong(key, index));
        index += 8;
      } while (index <= lb);

      h64 = Long.rotateLeft(v1, 1) + Long.rotateLeft(v2, 7) + Long.rotateLeft(v3, 12) + Long.rotateLeft(v4, 18);
      h64 = merge(h64, v1);
      h64 = merge(h64, v2);
      h64 = merge(h64, v3);
      h64 = merge(h64, v4);
    } else {
      h64 = seed + PRIME_5;
    }
    h64 += length;
    while (index < length) {
      h64 ^= round(0, getLong(key, index));
      h64 = Long.rotateLeft(h64, 27) * PRIME_1 + PRIME_4;
      index += 8;
    }
    return avalanche(h64);
  }
}
