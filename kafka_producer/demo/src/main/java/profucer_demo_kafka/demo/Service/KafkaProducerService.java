//package profucer_demo_kafka.demo.Service;
//
//import org.apache.kafka.clients.producer.ProducerRecord;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
//import org.springframework.kafka.requestreply.RequestReplyFuture;
//import org.springframework.kafka.support.SendResult;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.TimeoutException;
//
//@Service
//public class KafkaProducerService {
//
//    @Autowired
//    private ReplyingKafkaTemplate<String, String, String> replyingKafkaTemplate;
//
//    public ConsumerRecord<String, String>  sendAndReceive(String message) throws InterruptedException, ExecutionException, TimeoutException {
//        // Create a producer record
//        ProducerRecord<String, String> record = new ProducerRecord<>("kRequests", message);
//
//        // Send and wait for a reply
//        RequestReplyFuture<String, String, String> future = replyingKafkaTemplate.sendAndReceive(record);
//
//        // Wait for the send result
//        SendResult<String, String> sendResult = future.getSendFuture().get(10, TimeUnit.SECONDS);
//        System.out.println("Sent message: " + sendResult.getProducerRecord().value());
//
//        // Wait for the reply
//        ConsumerRecord<String, String> reply = future.get(10, TimeUnit.SECONDS);
//        System.out.println("received message: " + reply.value());
//        return reply;
//    }
//}