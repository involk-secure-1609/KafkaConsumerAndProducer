package KafkaConsumer.TrialConsumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.support.Acknowledgment;

public class ConsumerListener implements AcknowledgingMessageListener {
    @Override
    public void onMessage(ConsumerRecord data, Acknowledgment acknowledgment) {

    }
}
