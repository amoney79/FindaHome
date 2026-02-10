#!/bin/bash
# Helper script to run Maven commands using the wrapper
java -Dmaven.multiModuleProjectDirectory="$PWD" -cp .mvn/wrapper/maven-wrapper.jar org.apache.maven.wrapper.MavenWrapperMain "$@"
