package id.db;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class XxHash64Test
{
  @Test
  void testHashes()
  {
    assertEquals(1, XxHash64.hash64(new byte[]{1, 2, 3, 4, 5, 6, 7, 8}, 0));
  }
}
