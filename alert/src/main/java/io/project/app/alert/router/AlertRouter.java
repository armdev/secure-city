package io.project.app.alert.producer;

import com.google.gson.Gson;
import io.project.app.alert.enums.IncidentEvent;
import static io.project.app.alert.enums.IncidentEvent.FIRING;
import io.project.app.alert.incident.IncidentData;
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

    public void router(IncidentData alert) {
        IncidentEvent event = IncidentEvent.valueOf(alert.getEvent());
        Gson gson = new Gson();
        switch (event) {
            case SHOOTING:
                log.info("Incident type is {} ", event);
                String alertShooting = gson.toJson(alert);
                alertProducer.sendMessageToPolice(alertShooting);
                alertProducer.sendMessageToAmbulance(alertShooting);
                break;
            case FIRING:
                log.info("Incident type is {} ", event);
                String alertFiring = gson.toJson(alert);
                alertProducer.sendMessageToPolice(alertFiring);
                break;
            case FIRE:
                log.info("Incident type is {} ", event);
                String alertFire = gson.toJson(alert);
                alertProducer.sendMessageToPolice(alertFire);
                alertProducer.sendMessageToAmbulance(alertFire);
                break;
            case HOOLIGANS:
                log.info("Incident type is {} ", event);
                String alertHooligans = gson.toJson(alert);
                alertProducer.sendMessageToPolice(alertHooligans);
                break;
            case CAR_INCIDENT:
                log.info("Incident type is {} ", event);
                String alertCarIncidents = gson.toJson(alert);
                alertProducer.sendMessageToPolice(alertCarIncidents);
                alertProducer.sendMessageToAmbulance(alertCarIncidents);
                break;
            default:
                log.error("Incident type is {}", event);
                break;
        }

    }

    public void sendToLakeHouse(IncidentData alert) {
        Gson gson = new Gson();
        String incident = gson.toJson(alert);
        alertProducer.sendMessageLakeHouse(incident);

    }
}
