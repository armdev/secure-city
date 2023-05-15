package io.project.app.police.helpers;

import io.project.app.police.domain.CarLocation;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

public class PoliceCarLocationGenerator {

    private static final double BERLIN_LATITUDE = 52.5200;
    private static final double BERLIN_LONGITUDE = 13.4050;
    private static final double MAX_DISTANCE_KM = 5.0;
    private static final double EARTH_RADIUS_KM = 6371.0;

    public static CarLocation generate() {

        double lat = BERLIN_LATITUDE + ThreadLocalRandom.current().nextDouble(-MAX_DISTANCE_KM, MAX_DISTANCE_KM) / EARTH_RADIUS_KM * (180 / Math.PI);
        double lon = BERLIN_LONGITUDE + ThreadLocalRandom.current().nextDouble(-MAX_DISTANCE_KM, MAX_DISTANCE_KM) / EARTH_RADIUS_KM * (180 / Math.PI) / Math.cos(BERLIN_LATITUDE * Math.PI / 180);
        CarLocation carLocation = new CarLocation("POINT", lat, lon, LocalDateTime.now());
        return carLocation;
    }

}
