package io.project.app.police.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class CarLocation implements Serializable {

    private String name;

    private Double lat;

    private Double lon;

    private LocalDateTime checkinDate;

}
