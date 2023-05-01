package io.project.app.lakehouse.consumer;

import com.google.gson.Gson;
import io.project.app.lakehouse.incident.IncidentData;


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
public class LakehouseDutyConsumer {


    @KafkaListener(topics = "lakehouse", groupId = "lakehouse-duty", concurrency = "3")
    public void pull(@Payload String payload,
            @Header(KafkaHeaders.KEY) String key,
            @Header(KafkaHeaders.TOPIC) String topic,
            @Header("X-Producer-Header") String header
    ) {
        log.info("From alert to Router");

        log.info("KEY '{}' ", key);
        log.info("Payload '{}' ", payload);

        Gson gson = new Gson();
        IncidentData alert = gson.fromJson(payload, IncidentData.class);

    
    }

}
