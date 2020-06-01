package undertone.com.logging;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.MessageAttributeValue;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Ignore;
import org.junit.Test;
import undertone.com.logging.dto.SqsMessage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Ignore
public class PutMessagesToSqs {

  private final static String sqsUrl = "https://sqs.us-east-1.amazonaws.com/054576017129/log-service-impression";

  private final static ObjectMapper objectMapper = new ObjectMapper();

  private final AmazonSQS loggingSQS = AmazonSQSClientBuilder.defaultClient();


  @Test
  public void sendToSqs() {

    sendToSqs(List.of(
      "{\"requestId\":\"requestId111\",\"utid\":\"utid111\",\"priceTag\":\"priceTag111\",\"uri\":\"uri111\",\"externalUserId\":\"externalUserId111\",\"zoneId\":\"zoneId111\",\"bannerId\":\"bannerId111\",\"paramBucket\":\"paramBucket111\"}",
      "{\"requestId\":\"requestId222\",\"utid\":\"utid222\",\"priceTag\":\"priceTag222\",\"uri\":\"uri222\",\"externalUserId\":\"externalUserId222\",\"zoneId\":\"zoneId222\",\"bannerId\":\"bannerId111\",\"paramBucket\":\"paramBucket222\"}"
    ));
    sendToSqs(List.of("{\"requestId\":\"requestId333\"}"));
  }

  private void sendToSqs(List<String> logRecords) {
    Map<String, MessageAttributeValue> messageAttributes = new HashMap<>();
    messageAttributes.put("contentType", new MessageAttributeValue().withDataType("String").withStringValue("application/json"));

    try {
      SqsMessage message = new SqsMessage(logRecords);

      SendMessageRequest sendMessageRequest = new SendMessageRequest().withMessageAttributes(messageAttributes);
      sendMessageRequest.setQueueUrl(sqsUrl);
      String json = objectMapper.writeValueAsString(message);
      sendMessageRequest.setMessageBody(json);

      loggingSQS.sendMessage(sendMessageRequest);

    } catch (Exception e) {
      log.error("Failed sending to logging SQS, {}", e.getMessage());
    }
  }
}
