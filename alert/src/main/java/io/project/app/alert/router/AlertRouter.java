package io.project.app.alert.producer;

import io.project.app.alert.enums.IncidentEvent;
import io.project.app.alert.model.GpsData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

/**
 *
 * @author armena
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class AlertRouter {

    private final AlertProducer alertProducer;

    public void router(GpsData alert) {
        IncidentEvent event = IncidentEvent.valueOf(alert.getEvent());

        switch (event) {
            case SHOOTING:
                log.info("Incident type is {}", event);
                break;
            case FIRING:
                log.info("Incident type is {}", event);
                break;
            case FIRE:
                log.info("Incident type is {}", event);
                break;
            case HOOLIGANS:
                log.info("Incident type is {}", event);
                break;
            case CAR_INCIDENT:
                log.info("Incident type is {}", event);
                break;
            default:
                log.error("Incident type is {}", event);
                break;
        }

    }
}
