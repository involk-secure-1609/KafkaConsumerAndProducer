package profucer_demo_kafka.demo.Listeners;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class ProducerListener implements org.springframework.kafka.support.ProducerListener {
    public void onSuccess(ProducerRecord producerRecord, RecordMetadata recordMetadata) {
            System.out.println(producerRecord.partition());
        System.out.println(producerRecord.headers());
        System.out.println(recordMetadata.serializedKeySize());
        System.out.println(recordMetadata.offset());
        System.out.println("Yeah producer listiner is working");
    }

        public void onError(ProducerRecord producerRecord, RecordMetadata recordMetadata, Exception exception) {
        System.out.println("error caught in the producerListener");
    }

}
