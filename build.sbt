import AssemblyKeys._ // put this at the top of the file

assemblySettings

name := "scandl"

version := "1.0"

scalaVersion := "2.10.3"

libraryDependencies ++= Seq(
 "org.jmock" % "jmock" % "2.6.0" % "test",
 "org.scalacheck" %% "scalacheck" % "1.10.1" % "test",
 "org.specs2" %% "specs2" % "2.3.10" % "test",
 "org.scalatest" % "scalatest_2.10" % "2.1.0" % "test",
 "com.github.wookietreiber" %% "scala-chart" % "latest.integration",
 "org.scalaj" %% "scalaj-http" % "0.3.14",
 "org.scalanlp" % "breeze_2.10" % "0.7",
 "org.scalanlp" % "breeze-natives_2.10" % "0.7",
 "org.skife.com.typesafe.config" % "typesafe-config" % "0.3.0"
)

//scalacOptions in Test ++= Seq("-Yrangepos")

resolvers ++= Seq("snapshots", "releases").map(Resolver.sonatypeRepo)

