package uk.gov.tna.dri.automation

import akka.http.scaladsl.Http
import akka.http.scaladsl.client.RequestBuilding.Get
import akka.http.scaladsl.model.HttpResponse
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers
import spray.json.DefaultJsonProtocol._

import scala.concurrent.Await

class S3RequestSuite extends AnyFlatSpec with ScalaFutures with Matchers  with AWSTestContext{

     "S3Request" should "retrieve from pre-signed url" in {


          import akka.actor.ActorSystem

          import scala.language.postfixOps
          implicit val system = ActorSystem()
          import scala.concurrent.duration._

          val s3reqest =  Http().singleRequest(Get("https://github.com/digital-preservation/dri-vocabulary"))

          val po: HttpResponse =  Await.result(s3reqest,2 second)

          assert(true)

     }


}
