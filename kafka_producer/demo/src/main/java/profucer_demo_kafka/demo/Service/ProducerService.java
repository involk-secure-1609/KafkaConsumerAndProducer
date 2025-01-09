package profucer_demo_kafka.demo.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.*;

@Service
@Slf4j
public class ProducerService {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;
    //generateRandomTransaction
    public String generateRandomTransaction(){
        String vendors[]={"Amazon","Paypal","Visa","mastercard"};
        String vendor= vendors[ThreadLocalRandom.current().nextInt(vendors.length)];
        double amount = ThreadLocalRandom.current().nextDouble(0.10,1000.0);
        return "Vendor: "+vendor+"Amount $"+amount;
    }

//    SendPaymentTransactions Asynchronously- this is preffered cuz a large no of transactions have to take
    // place at the same time
     @Scheduled(fixedRate = 2000)
    public void SendPaymentTransactionsAsynchronously(){
        String transaction= generateRandomTransaction();
        System.out.println("Sending payment transactions {}"+transaction);
        kafkaTemplate.send("payment-topic",transaction)
                .whenComplete(((sendResult, throwable) -> {
                    if(throwable!=null){
                        onFailure(throwable);
                    }else {
                        onSuccess(sendResult);
                    }
                }));

    }

    private void onSuccess(SendResult<String, String> sendResult) {
        System.out.printf("Received new metadata. \n" +
                        "Topic: {}, Partition: {}, Offset: {}, Timestamp: {}",
                sendResult.getRecordMetadata().topic(),
                sendResult.getRecordMetadata().partition(),
                sendResult.getRecordMetadata().offset(),
                sendResult.getRecordMetadata().timestamp());
    }

    private void onFailure(Throwable throwable) {
        System.out.println("Error occurred while producing the message {}"+throwable);
    }

    public String generateTransactionKey(){
        return UUID.randomUUID().toString();
    }


    //SendPaymentTransactions-2()synchronously

//    @Scheduled(fixedRate = 2000)
    public SendResult<String,String> SendPaymentTransactionsSynchronously() throws ExecutionException, InterruptedException, TimeoutException {
        String transaction= generateRandomTransaction();
        System.out.println("Sending payment transactions {}"+transaction);
        SendResult<String,String> sendResult = kafkaTemplate.send("payment-topic",generateTransactionKey(),transaction).get();
        System.out.printf("Received new metadata. \n" +
                        "Topic: %s, Partition: %d, Offset: %d, Timestamp: %d%n",
                sendResult.getRecordMetadata().topic(),
                sendResult.getRecordMetadata().partition(),
                sendResult.getRecordMetadata().offset(),
                sendResult.getRecordMetadata().timestamp());
        Message<String> message = MessageBuilder.withPayload(transaction)
                .setHeader("kafka_key", generateTransactionKey())
                .build();
        SendResult<String,String> sendResult1=kafkaTemplate.send(message).get(10, TimeUnit.SECONDS);
        System.out.printf("Received new metadata. \n" +
                        "Topic: %s, Partition: %d, Offset: %d, Timestamp: %d%n",
                sendResult1.getRecordMetadata().topic(),
                sendResult1.getRecordMetadata().partition(),
                sendResult1.getRecordMetadata().offset(),
                sendResult1.getRecordMetadata().timestamp());
        return sendResult;
    }
}