package io.project.app.alert.consumer;

import com.google.gson.Gson;
import io.project.app.alert.incident.IncidentData;
import io.project.app.alert.producer.AlertRouter;
import lombok.NonNull;

import lombok.RequiredArgsConstructor;

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
@RequiredArgsConstructor
public class AlertConsumer {
    
    private final AlertRouter alertService;
    
    @KafkaListener(topics = "alert", groupId = "alert-notify", concurrency = "3")
    public void receiveAndSendToAlertRouter(@Payload @NonNull String payload,
            @Header(KafkaHeaders.KEY) String key,
            @Header(KafkaHeaders.TOPIC) String topic,
            @Header("X-Producer-Header") String header
    ) {
        log.info("From alert to Router");
        
        log.info("KEY '{}' ", key);
        log.info("Payload '{}' ", payload);
        Gson gson = new Gson();
        IncidentData alert = gson.fromJson(payload, IncidentData.class);
        alertService.router(alert);
    }
    
    @KafkaListener(topics = "alert", groupId = "alert-store", concurrency = "3")
    public void receiveAndSendToLakeMicroserviceForPersistInThePostgres(@Payload @NonNull String payload,
            @Header(KafkaHeaders.KEY) String key,
            @Header(KafkaHeaders.TOPIC) String topic,
            @Header("X-Producer-Header") String header
    ) {
        log.info("From Alert to Postgres");
        
        log.info("KEY '{}' ", key);
        log.info("Payload '{}' ", payload);
        
        Gson gson = new Gson();
        IncidentData toJson = gson.fromJson(payload, IncidentData.class);
        
        alertService.sendToLakeHouse(toJson);
        
    }
}
