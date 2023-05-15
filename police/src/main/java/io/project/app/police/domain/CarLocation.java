package io.project.app.police.domain;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CarLocation implements Serializable {

    @Serial
    private static final long serialVersionUID = -8455878665254870279L;

    private String name;

    private Double lat;

    private Double lon;

    private LocalDateTime checkinDate;

    public CarLocation(String name, Double lat, Double lon, LocalDateTime checkinDate) {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.checkinDate = checkinDate;
    }
    
    

}
