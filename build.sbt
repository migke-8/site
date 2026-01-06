import xerial.sbt.Sonatype._

scalaVersion := "3.3.5"
organization := "io.github.migke-8"
name         := "site"
version      := "0.1.0"

scalacOptions ++= Seq(
  "-deprecation",
  "-feature",
  "-unchecked",
  "-Xfatal-warnings"
)

libraryDependencies ++= Seq(
  "org.scalameta" %% "munit" % "0.7.29" % Test
)

publishMavenStyle := true
publishTo := {
  val nexus = "https://s01.oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

licenses := List("MIT" -> url("https://opensource.org/licenses/MIT"))
homepage := Some(url("https://github.com/yourusername/my-awesome-library"))

scmInfo := Some(
  ScmInfo(
    url("https://github.com/migke-8/site"),
    "scm:git@github.com:migke-8/site.git"
  )
)

developers := List(
  Developer(
    id    = "migke-8",
    name  = "Miguel Peixoto Portela Bispo",
    email = "miguelportelabispo@gmail.com",
    url   = url("https://migke-8.github.io")
  )
)
sonatypeCredentialHost := sonatypeCentralHost

publishTo := sonatypePublishToBundle.value

publishArtifact in (Compile, packageSrc) := true
publishArtifact in (Compile, packageDoc) := true
publishTo := sonatypePublishToBundle.value
sonatypeCredentialHost := sonatypeCentralHost

versionScheme := Some("early-semver")

sonatypeBundleDirectory := (ThisBuild / baseDirectory).value / "target" / "sonatype-staging" / s"${version.value}"
