#! /bin/bash

bazel $1 --define=JAVA_HOME=$JAVA_HOME "${@:2}"
