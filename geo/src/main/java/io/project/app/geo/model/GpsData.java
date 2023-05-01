package io.project.app.geo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author armena
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GpsData implements Serializable {

    @JsonProperty("lat")
    private double lat;
    @JsonProperty("lon")
    private double lon;
    @JsonProperty("event")
    private String event;
    @JsonProperty("status")
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
