package io.project.app.lakehouse.producer;

import jakarta.validation.constraints.NotNull;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LakehouseProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private static final String POLICE_TOPIC = "police";


    @SneakyThrows
    @Transactional(transactionManager = "kafkaTransactionManager")
    public void sendMessageToHell(@NotNull String message) {
        String transactionId = UUID.randomUUID().toString();
        ProducerRecord<String, String> producerRecord
                = new ProducerRecord<>(POLICE_TOPIC, transactionId, message);
        producerRecord.headers().add(KafkaHeaders.TOPIC, POLICE_TOPIC.getBytes(StandardCharsets.UTF_8));
        producerRecord.headers().add(KafkaHeaders.KEY, transactionId.getBytes(StandardCharsets.UTF_8));
        producerRecord.headers().add("X-Producer-Header", "alert".getBytes(StandardCharsets.UTF_8));

        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(producerRecord);
        future.whenComplete((sendResult, throwable) -> {
            if (throwable != null) {
                // Handle failure
                log.error("@KAFKA FAIL: POLICE_TOPIC unable to send message='{}'", message, throwable);
            } else {
                // Handle success
                log.info("POLICE_TOPIC Message sent: " + sendResult.getProducerRecord().value());
            }
        });

    }

}
