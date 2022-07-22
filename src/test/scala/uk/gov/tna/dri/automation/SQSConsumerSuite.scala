package uk.gov.tna.dri.automation

import akka.stream.alpakka.sqs.SqsSourceSettings
import akka.stream.alpakka.sqs.scaladsl.SqsSource
import akka.stream.scaladsl.Sink
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import software.amazon.awssdk.services.sqs.model.SendMessageRequest

import java.util.concurrent.TimeUnit

class SQSConsumerSuite extends AnyFlatSpec with ScalaFutures with Matchers  with AWSTestContext{

     "SQSConsumer" should "consumes a message" in {
          val queueUrl: String = "{our queue}"

          val sendMessageRequest =
               SendMessageRequest
                 .builder()
                 .queueUrl(queueUrl)
                 .messageBody("alpakka")
                 .build()

          val sqsClient = createAsyncClient(queueUrl)
          sqsClient.sendMessage(sendMessageRequest).get(2, TimeUnit.SECONDS)
          val settings = SqsSourceSettings.Defaults.withWaitTimeSeconds(0)

          val future = SqsSource(queueUrl, settings)(sqsClient)
            .runWith(Sink.head)

          future.futureValue.body() shouldBe "alpakka"
     }
}
