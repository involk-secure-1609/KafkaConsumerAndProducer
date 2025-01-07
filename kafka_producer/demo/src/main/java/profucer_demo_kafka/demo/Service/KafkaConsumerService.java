//package profucer_demo_kafka.demo.Service;
//
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.apache.kafka.clients.producer.ProducerRecord;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.support.KafkaHeaders;
//import org.springframework.messaging.handler.annotation.Header;
//import org.springframework.stereotype.Service;
//
//@Service
//public class KafkaConsumerService {
//
//    @Autowired
//    private KafkaTemplate<String, String> kafkaTemplate;
//
//    @KafkaListener(topics = "kRequests",groupId = "repliesGroup")
//    public void listen(ConsumerRecord<String, String> record) {
//        // Process the request
//        String replyPayload = "Processed: " + record.value().toUpperCase();
//
//        // Send the reply
//        ProducerRecord<String, String> replyRecord = new ProducerRecord<>("kReplies", replyPayload);
//        replyRecord.headers().add(KafkaHeaders.CORRELATION_ID, record.headers().lastHeader(KafkaHeaders.CORRELATION_ID).value());
//        kafkaTemplate.send(replyRecord);
//    }
//}