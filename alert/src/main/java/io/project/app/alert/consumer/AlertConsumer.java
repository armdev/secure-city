package io.project.app.alert.consumer;

import com.google.gson.Gson;
import io.project.app.alert.model.GpsData;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

/**
 *
 * @author armena
 */
@Service
@Slf4j
public class AlertConsumer {

    @KafkaListener(topics = "alert", groupId = "alert-notify", concurrency = "3")
    public void first(@Payload String payload,
            @Header(KafkaHeaders.KEY) String key,
            @Header(KafkaHeaders.TOPIC) String topic,
            @Header("X-Producer-Header") String header
    ) {
        log.info("----------- Alert 1 start -----------");

        log.info("KEY '{}' ", key);

        log.info("header '{}' ", header);
        log.info("TOPIC '{}' ", topic);

        Gson gson = new Gson();
        GpsData toJson = gson.fromJson(payload, GpsData.class);

        log.info("Payload '{}' ", toJson.toString());

        log.info("----------- Alert 1 done -----------");
    }

    @KafkaListener(topics = "alert", groupId = "alert-store", concurrency = "3")
    public void second(@Payload String payload,
            @Header(KafkaHeaders.KEY) String key,
            @Header(KafkaHeaders.TOPIC) String topic,
            @Header("X-Producer-Header") String header
    ) {
        log.info("----------- Alert 2 start -----------");

        log.info("KEY '{}' ", key);

        log.info("header '{}' ", header);
        log.info("TOPIC '{}' ", topic);

        Gson gson = new Gson();
        GpsData toJson = gson.fromJson(payload, GpsData.class);

        log.info("Payload '{}' ", toJson.toString());

        log.info("----------- Alert 2 done -----------");
    }
}
