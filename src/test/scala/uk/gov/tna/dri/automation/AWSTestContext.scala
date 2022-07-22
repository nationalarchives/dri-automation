package uk.gov.tna.dri.automation


import akka.actor.ActorSystem
import com.github.matsluni.akkahttpspi.AkkaHttpClient
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.sqs.SqsAsyncClient

import java.net.URI

trait AWSTestContext {

  // set environment values AWS_ACCESS_KEY_ID and AWS_SECRET_ACCESS_KEY
  implicit val system: ActorSystem = ActorSystem()
  def createAsyncClient(sqsEndpoint: String): SqsAsyncClient = {
      implicit val awsSqsClient = SqsAsyncClient
      .builder()
      .credentialsProvider(EnvironmentVariableCredentialsProvider.create())
      .endpointOverride(URI.create(sqsEndpoint))
      .region(Region.EU_WEST_2)
      .httpClient(AkkaHttpClient.builder().withActorSystem(system).build())
      .build()


    awsSqsClient
  }

}
