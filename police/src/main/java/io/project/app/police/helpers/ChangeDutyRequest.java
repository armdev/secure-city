package io.project.app.police.helpers;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author armen
 */
@Data
public class ChangeDutyRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 7771274000023739194L;

    private String policeCarId;

    private String existingOfficerId;

    private String newOfficerId;

}
