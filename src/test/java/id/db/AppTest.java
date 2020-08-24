package id.db;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.util.List;

final class AppTest
{
  static int[] toArray(List<Integer> list)
  {
    var a = new int[list.size()];
    for (int i = 0; i < a.length; i++) {
      a[i] = list.get(i);
    }
    return a;
  }

  @Test
  void testNumber()
  {
    var app = new App();
    assertArrayEquals(new int[]{42}, toArray(app.getNumbers()));
  }
}
