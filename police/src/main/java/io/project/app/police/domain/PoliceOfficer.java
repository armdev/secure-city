package io.project.app.police.domain;

import java.io.Serial;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

/**
 *
 * @author armena
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PoliceOfficer implements Serializable {

    @Serial
    private static final long serialVersionUID = -7340797464295033378L;

    @Id
    private String id;

    private String name;

    private String badgeNumber;

    private Integer age;

    public PoliceOfficer(String name, String badgeNumber, Integer age) {
        this.name = name;
        this.badgeNumber = badgeNumber;
        this.age = age;
    }

}
