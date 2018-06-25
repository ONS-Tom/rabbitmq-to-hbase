lazy val root = (project in file(".")).settings(
  name := "message-queue-poc",
  scalaVersion := "2.11.8",
  fork in run := true,
  mainClass := Some("Subscriber"),
  libraryDependencies ++= Seq(
    "com.newmotion"               %%  "akka-rabbitmq"   % "5.0.0",
    "ch.qos.logback"              %   "logback-classic" % "1.2.3", // Needed for scala-logging
    "com.typesafe.scala-logging"  %%  "scala-logging"   % "3.9.0",
    "org.scalamock"               %%  "scalamock"       % "4.1.0"     % Test,
    "com.github.tomakehurst"      %   "wiremock"        % "1.58"      % Test,
    "org.scalatest"               %%  "scalatest"       % "3.0.0"     % Test
  ),
  scalacOptions in ThisBuild ++= Seq(
    "-language:experimental.macros",
    "-target:jvm-1.8",
    "-encoding", "UTF-8",
    "-language:reflectiveCalls",
    "-language:experimental.macros",
    "-language:implicitConversions",
    "-language:higherKinds",
    "-language:postfixOps",
    "-deprecation", // warning and location for usages of deprecated APIs
    "-feature", // warning and location for usages of features that should be imported explicitly
    "-unchecked", // additional warnings where generated code depends on assumptions
    "-Xlint", // recommended additional warnings
    "-Ywarn-adapted-args", // Warn if an argument list is modified to match the receiver
    "-Ywarn-value-discard", // Warn when non-Unit expression results are unused
    "-Ywarn-inaccessible", // Warn about inaccessible types in method signatures
    "-Ywarn-dead-code", // Warn when dead code is identified
    "-Ywarn-unused", // Warn when local and private vals, vars, defs, and types are unused
    "-Ywarn-unused-import" //  Warn when imports are unused (don't want IntelliJ to do it automatically)
  )
)
