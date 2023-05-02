package io.project.app.incident.services;

import io.project.app.incident.model.IncidentData;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class GpsDataGenerator {

    private static final double BERLIN_LATITUDE = 52.5200;
    private static final double BERLIN_LONGITUDE = 13.4050;
    private static final double MAX_DISTANCE_KM = 5.0;
    private static final double EARTH_RADIUS_KM = 6371.0;

    public static IncidentData generate() {
        double lat = BERLIN_LATITUDE + ThreadLocalRandom.current().nextDouble(-MAX_DISTANCE_KM, MAX_DISTANCE_KM) / EARTH_RADIUS_KM * (180 / Math.PI);
        double lon = BERLIN_LONGITUDE + ThreadLocalRandom.current().nextDouble(-MAX_DISTANCE_KM, MAX_DISTANCE_KM) / EARTH_RADIUS_KM * (180 / Math.PI) / Math.cos(BERLIN_LATITUDE * Math.PI / 180);
        return new IncidentData(lat, lon);
    }

    public static IncidentData generateWithEvent() {
        double lat = BERLIN_LATITUDE + ThreadLocalRandom.current().nextDouble(-MAX_DISTANCE_KM, MAX_DISTANCE_KM) / EARTH_RADIUS_KM * (180 / Math.PI);
        double lon = BERLIN_LONGITUDE + ThreadLocalRandom.current().nextDouble(-MAX_DISTANCE_KM, MAX_DISTANCE_KM) / EARTH_RADIUS_KM * (180 / Math.PI) / Math.cos(BERLIN_LATITUDE * Math.PI / 180);

        String event = RandomEvent.randomEvent().name();

        String status = RandomStatus.randomStatus().name();

        String transactionId = UUID.randomUUID().toString();
        return new IncidentData(lat, lon, event, status, transactionId);
    }
}

enum RandomEvent {
    SHOOTING, FIRING, FIRE, HOOLIGANS, CAR_INCIDENT;

    private static final Random PRNG = new Random();

    public static RandomEvent randomEvent() {
        RandomEvent[] directions = values();
        return directions[PRNG.nextInt(directions.length)];
    }
}

enum RandomStatus {
    STARTED, IN_PROGRESS, FINSIHED;

    private static final Random PRNG = new Random();

    public static RandomStatus randomStatus() {
        RandomStatus[] directions = values();
        return directions[PRNG.nextInt(directions.length)];
    }
}
