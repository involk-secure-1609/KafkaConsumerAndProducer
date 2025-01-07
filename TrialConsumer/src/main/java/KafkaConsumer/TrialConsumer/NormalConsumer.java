package KafkaConsumer.TrialConsumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;

public class NormalConsumer {
    @KafkaListener(groupId = "myGroup", topics = "payment-topic")
    public void listen(ConsumerRecord<String, String> record) {
        System.out.println(record.key());
        System.out.println(record.value());
        System.out.println("received");
    }
}
