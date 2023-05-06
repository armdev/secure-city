/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.project.app.police.resources;

import io.project.app.police.domain.CarLocation;
import io.project.app.police.domain.PoliceCar;
import io.project.app.police.services.PoliceCarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author user
 */
@RestController
@RequestMapping("/api/police-cars")
public class PoliceCarController {

    private final PoliceCarService policeCarService;

    public PoliceCarController(PoliceCarService policeCarService) {
        this.policeCarService = policeCarService;
    }

    @PostMapping
    public ResponseEntity<PoliceCar> createNewPoliceCar(@RequestBody PoliceCar policeCar) {
        PoliceCar createdCar = policeCarService.createNewPoliceCar(policeCar);
        return ResponseEntity.ok(createdCar);
    }

    @PostMapping("/{policeCarId}/duty")
    public ResponseEntity<PoliceCar> createNewDutyAndUpdateHistory(@PathVariable String policeCarId,
                                                                   @RequestParam String primaryOfficerId,
                                                                   @RequestParam String secondaryOfficerId) {
        PoliceCar updatedCar = policeCarService.createNewDutyAndUpdateHistory(policeCarId, primaryOfficerId, secondaryOfficerId);
        return ResponseEntity.ok(updatedCar);
    }

    @PutMapping("/{policeCarId}/duty/{existingOfficerId}")
    public ResponseEntity<PoliceCar> changeOfficerInTheDutyAndUpdateHistory(@PathVariable String policeCarId,
                                                                            @PathVariable String existingOfficerId,
                                                                            @RequestParam String newOfficerId) {
        PoliceCar updatedCar = policeCarService.changeOfficerInTheDutyAndUpdateHistory(policeCarId, existingOfficerId, newOfficerId);
        return ResponseEntity.ok(updatedCar);
    }

    @PutMapping("/{policeCarId}/location")
    public ResponseEntity<PoliceCar> updateCurrentLocationAndHistory(@PathVariable String policeCarId,
                                                                      @RequestBody CarLocation carLocation) {
        PoliceCar updatedCar = policeCarService.updateCurrentLocationAndHistory(policeCarId, carLocation);
        return ResponseEntity.ok(updatedCar);
    }
}
