package io.project.app.police.domain;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "police_officer", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class PoliceOfficer implements Serializable {

    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "POLICE_OFFICER_SEQ_GEN", allocationSize = 1, sequenceName = "POLICE_OFFICER_SEQ_GEN")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POLICE_OFFICER_SEQ_GEN")
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "badge_number")
    private String badgeNumber;
    @Column(name = "age")
    private Integer age;

}
