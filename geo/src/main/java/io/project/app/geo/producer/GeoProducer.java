package io.project.app.geo.producer;

import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;

import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class GeoProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @SneakyThrows
    @Transactional
    public void sendMessage(String message) {
        String transactionId = UUID.randomUUID().toString();
        ProducerRecord<String, String> producerRecord
                = new ProducerRecord<>("alert", transactionId, message);
        producerRecord.headers().add(KafkaHeaders.TOPIC, "alert".getBytes(StandardCharsets.UTF_8));
        producerRecord.headers().add(KafkaHeaders.KEY, transactionId.getBytes(StandardCharsets.UTF_8));
        producerRecord.headers().add("X-Producer-Header", "geo".getBytes(StandardCharsets.UTF_8));
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(producerRecord);
                future.whenComplete((sendResult, throwable) -> {
                    if (throwable != null) {
                        // Handle failure
                        log.error("@KAFKA FAIL: tpoutput unable to send message='{}'", message, throwable);
                    } else {
                        // Handle success
                        log.info("Message sent: " + sendResult.getProducerRecord().value());
                    }
                });

            }
        });
    }

}
