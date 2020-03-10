package com.sksamuel.elastic4s.http

import com.sksamuel.elastic4s.testkit.DockerTests

import scala.util.Try
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ElasticClientTests extends AnyFlatSpec with Matchers with DockerTests {

  Try {
    client.execute {
      deleteIndex("testindex")
    }.await
  }

  "DefaultHttpClient" should "support utf-8" in {
    client.execute {
      indexInto("testindex").doc("""{ "text":"¡Hola! ¿Qué tal?" }""")
    }.await.result.result shouldBe "created"
  }
}
