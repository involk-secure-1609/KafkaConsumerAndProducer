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

    //ProducerFactory

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
           KafkaTemplate kafkaTemplate=   new KafkaTemplate<>(producerFactory());
           kafkaTemplate.setProducerListener(new ProducerListener() {
           });
           return kafkaTemplate;
    }

    //NewTopic
    @Bean
    public NewTopic paymentTopic(){
        return new NewTopic("payment-topic",3,(short) 1);
    }
}
