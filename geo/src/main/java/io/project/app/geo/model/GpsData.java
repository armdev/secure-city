package io.project.app.geo.model;

import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author armena
 */
@Data
public class GpsData implements Serializable {

    private double lat;

    private double lon;
    
    private String event;
    
    private String status;

    public GpsData(double lat, double lon, String event, String status) {
        this.lat = lat;
        this.lon = lon;
        this.event = event;
        this.status = status;
    }

    public GpsData(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

}
