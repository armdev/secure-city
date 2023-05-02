package io.project.app.police.domain;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author armena
 */
@Data
@Entity
@Table(name = "police_car", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class PoliceCar implements Serializable {

    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "POLICE_CAR_SEQ_GEN", allocationSize = 1, sequenceName = "POLICE_CAR_SEQ_GEN")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POLICE_CAR_SEQ_GEN")
    @Column(name = "id")
    private Long id;
    @Column(name = "make")
    private String make;
    @Column(name = "model")
    private String model;
    @Column(name = "year")    
    private Integer year;
    @Column(name = "duty")    
    private String duty;
    @Column(name = "car_number")
    private String carNumber;    
    @Column(name = "register_date")
    private LocalDateTime registerDate;    
    @Column(name = "available")
    private boolean available;
    
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private List<CurrentDutyAssignment> assignments;

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
