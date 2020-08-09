load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

RULE_JVM_EXTERNAL_TAG = "3.3"
RULE_JVM_EXTERNAL_SHA = "d85951a92c0908c80bd8551002d66cb23c3434409c814179c0ff026b53544dab"

http_archive(
  name = "rules_jvm_external",
  strip_prefix = "rules_jvm_external-%s" % RULE_JVM_EXTERNAL_TAG,
  sha256 = RULE_JVM_EXTERNAL_SHA,
  url = "https://github.com/bazelbuild/rules_jvm_external/archive/%s.zip" % RULE_JVM_EXTERNAL_TAG,
)

load("@rules_jvm_external//:defs.bzl", "maven_install")

maven_install(
    artifacts = [
      "com.beust:jcommander:1.78",
    ],
    repositories = [
        "https://repo1.maven.org/maven2",
    ],
)
