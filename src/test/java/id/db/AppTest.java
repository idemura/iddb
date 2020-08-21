package id.db;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

final class AppTest {
  static int[] toArray(List<Integer> list) {
    var a = new int[list.size()];
    for (int i = 0; i < a.length; i++) {
      a[i] = list.get(i);
    }
    return a;
  }

  @Test
  void testNumber() {
    var app = new App();
    assertArrayEquals(new int[] {42}, toArray(app.getNumbers()));
  }
}
