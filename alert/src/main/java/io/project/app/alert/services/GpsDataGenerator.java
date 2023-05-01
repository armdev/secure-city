package io.project.app.alert.services;

import io.project.app.alert.model.GpsData;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class GpsDataGenerator {

    private static final double BERLIN_LATITUDE = 52.5200;
    private static final double BERLIN_LONGITUDE = 13.4050;
    private static final double MAX_DISTANCE_KM = 5.0;
    private static final double EARTH_RADIUS_KM = 6371.0;

    public static GpsData generate() {
        double lat = BERLIN_LATITUDE + ThreadLocalRandom.current().nextDouble(-MAX_DISTANCE_KM, MAX_DISTANCE_KM) / EARTH_RADIUS_KM * (180 / Math.PI);
        double lon = BERLIN_LONGITUDE + ThreadLocalRandom.current().nextDouble(-MAX_DISTANCE_KM, MAX_DISTANCE_KM) / EARTH_RADIUS_KM * (180 / Math.PI) / Math.cos(BERLIN_LATITUDE * Math.PI / 180);
        return new GpsData(lat, lon);
    }

    public static GpsData generateWithEvent() {
        double lat = BERLIN_LATITUDE + ThreadLocalRandom.current().nextDouble(-MAX_DISTANCE_KM, MAX_DISTANCE_KM) / EARTH_RADIUS_KM * (180 / Math.PI);
        double lon = BERLIN_LONGITUDE + ThreadLocalRandom.current().nextDouble(-MAX_DISTANCE_KM, MAX_DISTANCE_KM) / EARTH_RADIUS_KM * (180 / Math.PI) / Math.cos(BERLIN_LATITUDE * Math.PI / 180);

        String event = RandomEvent.randomEvent().name();

        String status = RandomStatus.randomStatus().name();
        return new GpsData(lat, lon, event, status);
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
    STARTED, IN_PROGRESS, FINSIHED ;

    private static final Random PRNG = new Random();

    public static RandomStatus randomStatus() {
        RandomStatus[] directions = values();
        return directions[PRNG.nextInt(directions.length)];
    }
}