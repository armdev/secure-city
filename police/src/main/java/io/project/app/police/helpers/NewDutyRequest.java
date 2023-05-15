/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.project.app.police.helpers;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author armen
 */
@Data
public class NewDutyRequest implements  Serializable{

    @Serial
    private static final long serialVersionUID = 7771274000023739194L;
    
    private String policeCarId;
    
    private String primaryOfficerId;
    
    private String secondaryOfficerId;
    
    
}
