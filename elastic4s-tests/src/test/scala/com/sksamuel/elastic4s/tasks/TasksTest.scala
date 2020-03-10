package com.sksamuel.elastic4s.tasks

import com.sksamuel.elastic4s.testkit.DockerTests
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class TasksTest extends AnyFlatSpec with DockerTests with Matchers {

  "list tasks" should "include all fields" in {

    val resp = client.execute {
      listTasks()
    }.await.result

    resp.nodes.head._2.roles should contain allElementsOf (Seq("master", "data", "ingest"))
    resp.nodes.head._2.tasks.values.forall(_.startTimeInMillis > 0) shouldBe true
  }

}
