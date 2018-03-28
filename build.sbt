

name := "faa-streaming"

version := "0.1"

//scalaVersion := "2.10.7"
scalaVersion := "2.11.8"

//assemblySettings

//resolvers += Resolver.url("artifactory", url("http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases"))(Resolver.ivyStylePatterns)


libraryDependencies ++= Seq(
  "org.apache.poi" % "poi-ooxml" % "3.11",
  "org.apache.poi" % "poi" % "3.11",
  "org.scalatest" %% "scalatest" % "3.0.1"  ,
  "com.eed3si9n" % "sbt-assembly" % "0.14.3",
  "com.microsoft.azure" %% "spark-streaming-eventhubs" % "2.1.5",
  "com.google.inject.extensions" % "guice-assistedinject" % "4.2.0"

)
addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.3")



initialCommands in console += " import org.apache.poi._"

exportJars := true

//mappings in (Compile, packageBin) ~= { _.filter(!_._1.getName.endsWith(".java")) }
sources in (Compile) ~= (_ filter (_.getName endsWith ".scala"))
mainClass in Compile := Some("com.honeywell.faa.readxls")
mainClass in(Compile, run) := Some("com.honeywell.faa.readxls")
mainClass in(Compile, packageBin) := Some("com.honeywell.faa.readxls")
mainClass in assembly := Some("com.honeywell.faa.readxls")