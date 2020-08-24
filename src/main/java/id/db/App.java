package id.db;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.google.common.collect.ImmutableList;
import java.util.List;

public final class App
{
  @Parameter(names = {"--user", "-u"})
  String user = "default";

  List<Integer> getNumbers()
  {
    return ImmutableList.of(42);
  }

  void run()
  {
    System.out.println("user=" + user);
  }

  public static void main(String[] args)
  {
    var app = new App();
    JCommander.newBuilder().addObject(app).build().parse(args);
    app.run();
  }
}
