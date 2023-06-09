package io.project.app.ambulance.incident;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author armena
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class IncidentData implements Serializable {

    @Serial
    private static final long serialVersionUID = 2655351983988378173L;

    @JsonProperty("lat")
    private double lat;
    @JsonProperty("lon")
    private double lon;
    @JsonProperty("event")
    private String event;
    @JsonProperty("status")
    private String status;
    @JsonProperty("transactionId")
    private String transactionId;

    public IncidentData(double lat, double lon, String event, String status) {
        this.lat = lat;
        this.lon = lon;
        this.event = event;
        this.status = status;
    }

    public IncidentData(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

}
