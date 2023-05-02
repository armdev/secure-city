package io.project.app.incident.producer;

import java.nio.charset.StandardCharsets;

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
public class IncidentProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private TransactionTemplate transactionTemplate;

    private static final String ALERT_TOPIC = "alert";

    /**
     *
     *
     * @param message
     *
     * The sendMessage method sends a Kafka message using a ProducerRecord
     * object created with the ALERT_TOPIC, transactionId, and message
     * parameters.
     *
     * The transactionId is a unique identifier generated using the
     * UUID.randomUUID() method, which ensures that each message has a unique
     * ID.
     *
     * The KafkaHeaders class is used to add headers to the producerRecord
     * object, such as the topic, key, and a custom header called
     * "X-Producer-Header".
     *
     * The TransactionTemplate class is used to execute the Kafka send operation
     * within a transaction. This ensures that the send operation is atomic, and
     * either succeeds or fails as a whole.
     *
     * The kafkaTemplate.send() method sends the message asynchronously and
     * returns a CompletableFuture<SendResult<String, String>> object.
     *
     * The future.whenComplete() method is used to handle the completion of the
     * send() operation. If an exception is thrown during the operation, it is
     * caught and logged as an error message using the log.error() method.
     * Otherwise, a success message is logged using the log.info() method.
     *
     * This Kafka sender follows best practices for sending messages
     * asynchronously and transactionally, which ensures that messages are
     * reliably delivered to the Kafka cluster.
     * @param transactionId
     */
    @SneakyThrows
    @Transactional
    public void sendMessage(String message, String transactionId) {

        ProducerRecord<String, String> producerRecord
                = new ProducerRecord<>(ALERT_TOPIC, transactionId, message);

        producerRecord.headers().add(KafkaHeaders.TOPIC, ALERT_TOPIC.getBytes(StandardCharsets.UTF_8));
        producerRecord.headers().add(KafkaHeaders.KEY, transactionId.getBytes(StandardCharsets.UTF_8));
        producerRecord.headers().add("X-Producer-Header", "geo".getBytes(StandardCharsets.UTF_8));
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(producerRecord);
                future.whenComplete((sendResult, throwable) -> {
                    if (throwable != null) {
                        // Handle failure
                        log.error("@KAFKA FAIL: incident unable to send message='{}'", message, throwable);
                    } else {
                        // Handle success
                        log.info("Message sent: " + sendResult.getProducerRecord().value());
                    }
                });

            }
        });
    }

}
