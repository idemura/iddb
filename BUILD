java_runtime(
  name = "jdk",
  java_home = "$(JAVA_HOME)",
)

java_binary(
  name = "iddb",
  srcs = glob(["src/main/java/id/db/*.java"]),
  deps = [
    "@maven//:com_beust_jcommander",
  ],
  main_class = "id.db.Main",
)
