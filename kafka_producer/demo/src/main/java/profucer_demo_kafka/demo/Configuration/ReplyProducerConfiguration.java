//package profucer_demo_kafka.demo.Configuration;
//
//import org.apache.kafka.clients.admin.NewTopic;
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.config.TopicBuilder;
//import org.springframework.kafka.core.ConsumerFactory;
//import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
//import org.springframework.kafka.core.DefaultKafkaProducerFactory;
//import org.springframework.kafka.core.ProducerFactory;
//import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
//import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//public class ReplyProducerConfiguration {
//
//    @Bean
//    public ProducerFactory<String, String> producerFactory() {
//        Map<String, Object> configProps = new HashMap<>();
//        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        return new DefaultKafkaProducerFactory<>(configProps);
//    }
//
//    @Bean
//    public ConsumerFactory<String, String> consumerFactory() {
//        Map<String, Object> configProps = new HashMap<>();
//        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,  "localhost:9092");
//        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "repliesGroup");
//        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        return new DefaultKafkaConsumerFactory<>(configProps);
//    }
//
//    @Bean
//    public ConcurrentMessageListenerContainer<String, String> replyContainer(
//            ConcurrentKafkaListenerContainerFactory<String, String> containerFactory) {
//        ConcurrentMessageListenerContainer<String, String> container =
//                containerFactory.createContainer("kReplies"); // Reply topic
//        container.getContainerProperties().setGroupId("repliesGroup"); // Unique group ID for replies
//        return container;
//    }
//
//    @Bean
//    public ReplyingKafkaTemplate<String, String, String> replyingKafkaTemplate(
//            ProducerFactory<String, String> producerFactory,
//            ConcurrentMessageListenerContainer<String, String> replyContainer) {
//        return new ReplyingKafkaTemplate<>(producerFactory, replyContainer);
//    }
//    @Bean
//    public NewTopic kRequests() {
//        return TopicBuilder.name("kRequests")
//                .partitions(1)
//                .replicas(1)
//                .build();
//    }
//
//    @Bean
//    public NewTopic kReplies() {
//        return TopicBuilder.name("kReplies")
//                .partitions(1)
//                .replicas(1)
//                .build();
//    }
//
//}
