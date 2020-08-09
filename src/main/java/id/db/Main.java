package id.db;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

public final class Main {
  @Parameter(names = {"--user", "-u"})
  String user = "default";

  void run() {
    System.out.println("user=" + user);
  }

  public static void main(String[] args) {
    var main = new Main();
    JCommander.newBuilder().addObject(main).build().parse(args);
    main.run();
  }
}
