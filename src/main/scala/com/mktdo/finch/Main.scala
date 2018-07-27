package com.mktdo.finch

import com.twitter.conversions.time._
import com.twitter.finagle.{ Http, Service }
import com.twitter.finagle.http
import com.twitter.util.Await
import io.circe.generic.auto._
import io.finch._
import io.finch.circe._
import io.finch.syntax._
import scalikejdbc._
import shapeless._

object Main extends App {

  Class.forName("ru.yandex.clickhouse.ClickHouseDriver")
  ConnectionPool.singleton("jdbc:clickhouse://localhost:8123/system", "", "")

  implicit val sessoin = AutoSession

  case class System(table: String, bytes: Long)
  object System extends SQLSyntaxSupport[System] {
    override val tableName = "parts"
    def apply(rs: WrappedResultSet) = new System(
      rs.string("table"), rs.long("bytes"))
  }

  val hello: Endpoint[String] = get("hello") {
    Ok("world")
  }

  val select: Endpoint[List[System]] = get("select") {
    Ok(sql"""
      select
        table,
        SUM(bytes) as bytes
      from
        parts
      where
        active
      group by
        table;
        """.map(rs => System(rs)).list.apply())
  }

  val endpoint = hello :+: select

  Await.ready(Http.server.serve(":8081", endpoint.toService))
}

