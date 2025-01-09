package KafkaConsumer.TrialConsumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;

public class NormalConsumer {

    /*
    KafkaListener internally calls the poll() method
    Subscription:

Before calling poll(),
the consumer must subscribe to one or more topics (or assign specific partitions) using
the subscribe() or assign() methods.

Polling:
The poll() method fetches records from the Kafka broker(s) for the subscribed topics/partitions.
It returns a batch of records (if available) within the specified timeout period.
If no records are available, it returns an empty collection.

Offset Management:
The poll() method also handles offset management.
It keeps track of the last consumed offset for each partition and ensures that the consumer
resumes from the correct offset in case of a restart.

Heartbeat:

While polling, the consumer sends periodic heartbeats to the Kafka broker to indicate
that it is alive and part of the consumer group. This is crucial for maintaining the
consumer's membership in the group.

Rebalancing:

If a rebalance occurs (e.g., a new consumer joins or leaves the group),
the poll() method ensures that the consumer is assigned a new set of partitions
and resumes fetching records from the appropriate offsets.
     */
    @KafkaListener(groupId = "myGroup", topics = "payment-topic")
    public void listen(ConsumerRecord<String, String> record, Acknowledgment acknowledgment) {
        System.out.println(record.key());
        System.out.println(record.value());
        System.out.println("received");
        acknowledgment.acknowledge();
    }
}

