/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.project.app.police.incident;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.repository.query.Param;

@Data

public class DutyAssignmentDto implements Serializable {

    public DutyAssignmentDto() {
    }

    private Long assignmentId;
    private Long carId;
    private String carNumber;
    private List<Long> officerIds;
    private List<String> officerNames;

    public DutyAssignmentDto(Long assignmentId, Long carId, String carNumber, Long officerId, String officerName) {
        this.assignmentId = assignmentId;
        this.carId = carId;
        this.carNumber = carNumber;
        this.officerIds = new ArrayList<>();
        this.officerNames = new ArrayList<>();
        this.officerIds.add(officerId);
        this.officerNames.add(officerName);
    }

}
