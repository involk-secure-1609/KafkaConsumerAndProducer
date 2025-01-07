//package KafkaConsumer.TrialConsumer.ConsumerCfg;
//
//import com.sun.tools.jconsole.JConsoleContext;
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
//import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
//import org.springframework.kafka.listener.ContainerProperties;
//import org.springframework.kafka.listener.KafkaMessageListenerContainer;
//import org.springframework.kafka.listener.MessageListener;
//import org.springframework.stereotype.Service;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Service
//public class ConsumerService {
//
//
//    public ConcurrentMessageListenerContainer concurrentMessageListenerContainer(){
//        ContainerProperties containerProps = new ContainerProperties("payment-topic");
//
//        Map<String,Object> consumerProps= new HashMap<>();
//        consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
//        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG, "repliesGroup");
//        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        DefaultKafkaConsumerFactory<String, String> cf = new DefaultKafkaConsumerFactory<>(consumerProps);
//        ConcurrentMessageListenerContainer<String, String> container = new ConcurrentMessageListenerContainer<>(cf, containerProps);
//        container.setConcurrency(3);
//        return container;
//
//    }
//
//
//}
