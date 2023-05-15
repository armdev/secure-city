package io.project.app.police.helpers;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.io.Serial;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author armena
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PoliceCarCreationRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -7340797464295033378L;

    @NotNull
    @NotBlank
    private String make;
    
    @NotNull
    @NotBlank
    private String model;

    @Positive
    private Integer year;
    @NotNull
    @NotBlank
    private String duty;
    @NotNull
    @NotBlank
    private String carNumber;

}
