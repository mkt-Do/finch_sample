scalaVersion := "2.12.6"

libraryDependencies ++= Seq(
  "com.chuusai" %% "shapeless" % "2.3.3",
  "com.twitter" %% "finagle-http" % "18.5.0",
  "com.twitter" %% "finagle-mysql" % "18.5.0",
  "com.github.finagle" %% "finch-circe" % "0.20.0",
  "com.github.finagle" %% "finch-core" % "0.20.0",
  "io.circe" %% "circe-core" % "0.9.0",
  "io.circe" %% "circe-generic" % "0.9.0",
  "org.scalikejdbc" %% "scalikejdbc" % "3.2.+",
  "ru.yandex.clickhouse" % "clickhouse-jdbc" % "0.1.40"
)

