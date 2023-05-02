package io.project.app.ambulance.consumer;

import com.google.gson.Gson;
import io.project.app.ambulance.incident.IncidentData;


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
public class AmbulanceDutyConsumer {


    @KafkaListener(topics = "ambulance", groupId = "ambulance-duty", concurrency = "3")
    public void pull(@Payload String payload,
            @Header(KafkaHeaders.KEY) String key,
            @Header(KafkaHeaders.TOPIC) String topic,
            @Header("X-Producer-Header") String header
    ) {
        log.info("ambulance From alert to Router");

        log.info("ambulance KEY '{}' ", key);
        log.info("ambulance Payload '{}' ", payload);

        Gson gson = new Gson();
        IncidentData alert = gson.fromJson(payload, IncidentData.class);

    
    }

}
