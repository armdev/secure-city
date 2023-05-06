package io.project.app.police.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class PoliceDuty implements Serializable {

    private PoliceOfficer primaryOfficer;

    private PoliceOfficer secondaryOfficer;

    private LocalDateTime dutyDate;

}
