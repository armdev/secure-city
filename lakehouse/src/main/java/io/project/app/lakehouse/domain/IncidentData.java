package io.project.app.lakehouse.domain;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;

/**
 *
 * @author armena
 */
@Data
@Entity
@Table(name = "city_incidents", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class IncidentData implements Serializable {

    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "lat")
    private double lat;
    @Column(name = "lon")
    private double lon;
    @Column(name = "event")
    private String event;
    @Column(name = "status")
    private String status;
    @Column(name = "incident_date")
    private LocalDateTime registerDate;

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
