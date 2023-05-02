package io.project.app.police.domain;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author armena
 */
@Data
@Entity
@Table(name = "current_duty_assignments", schema = "public")
@AllArgsConstructor
@NoArgsConstructor  
public class CurrentDutyAssignment implements Serializable{
    
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "POLICE_DUTY_SEQ_GEN", allocationSize = 1, sequenceName = "POLICE_DUTY_SEQ_GEN")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POLICE_DUTY_SEQ_GEN")
    @Column(name = "id")
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "officer_id")
    private PoliceOfficer officer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_Id")
    private PoliceCar car;

    @Column(name = "lat")
    private Double lat;

    @Column(name = "lon")
    private Double lon;
    
    public CurrentDutyAssignment(PoliceOfficer officer, PoliceCar car, Double lat, Double lon) {
        this.officer = officer;
        this.car = car;
        this.lat = lat;
        this.lon = lon;
    }
}
