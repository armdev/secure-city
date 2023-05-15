/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.project.app.police.errors;

import java.io.Serializable;

/**
 *
 * @author armen
 */
public class OfficerNotFoundException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -3865682261895962060L;

    public OfficerNotFoundException() {
        super("Officer not found");
    }

    public OfficerNotFoundException(String message) {
        super(message);
    }

}
