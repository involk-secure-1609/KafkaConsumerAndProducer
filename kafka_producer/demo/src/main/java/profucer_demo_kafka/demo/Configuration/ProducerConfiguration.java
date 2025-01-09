package profucer_demo_kafka.demo.Configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.ProducerListener;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ProducerConfiguration {

    /* ProducerFactory- sets the strategy for producing messages
    Producer factory is thread safe so there will be one instance of it
    in the entire ApplicationContext.This is supposed to lead to higher performance
    We can make it
     */
    @Bean
    public ProducerFactory<String,String> producerFactory(){
        Map<String,Object> configMaps= new HashMap<>();
        configMaps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        configMaps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configMaps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        DefaultKafkaProducerFactory dkpf= new DefaultKafkaProducerFactory<>(configMaps);
        dkpf.setProducerPerThread(true);
//        dkpf.updateConfigs();
        return dkpf;
    }
    //KafkaTemplate
    @Bean
    public KafkaTemplate<String,String> kafkaTemplate(){
           KafkaTemplate kafkaTemplate=   new KafkaTemplate<String,String>(producerFactory());
           return kafkaTemplate;
    }

    //NewTopic- automatically adds a topic of this type
    @Bean
    public NewTopic paymentTopic(){
        return new NewTopic("payment-topic",3,(short) 1);
    }
}
