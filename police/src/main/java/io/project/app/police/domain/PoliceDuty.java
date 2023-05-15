package io.project.app.police.domain;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class PoliceDuty implements Serializable {

    @Serial
    private static final long serialVersionUID = 5434348501959786862L;

    private PoliceOfficer primaryOfficer;

    private PoliceOfficer secondaryOfficer;

    private LocalDateTime dutyDate;

}
