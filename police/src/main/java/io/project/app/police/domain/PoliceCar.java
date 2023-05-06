package io.project.app.police.domain;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 *
 * @author armena
 */
@Data
@Document(collection = "police_car")
@AllArgsConstructor
@NoArgsConstructor
public class PoliceCar implements Serializable {

    @Serial
    private static final long serialVersionUID = -7340797464295033378L;

    @Id
    private String id;
    @Field
    private String make;
    @Field
    private String model;
    @Field
    private Integer year;
    @Field
    private String duty;
    @Field
    private String carNumber;

    @Field
    private LocalDateTime registerDate;

    @Field
    private boolean available;

    private PoliceDuty policeDuty;

    private CarLocation currentLocation;

    private List<CarLocation> locationHistory = new ArrayList<>();

    private List<PoliceDuty> dutyHistory = new ArrayList<>();

    public PoliceCar(String make, String model, Integer year, String duty, String carNumber, LocalDateTime registerDate, boolean available) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.duty = duty;
        this.carNumber = carNumber;
        this.registerDate = registerDate;
        this.available = available;
    }

}
