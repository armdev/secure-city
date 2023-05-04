/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.project.app.police.services;

import io.project.app.police.domain.CurrentDutyAssignment;
import io.project.app.police.domain.PoliceCar;
import io.project.app.police.domain.PoliceOfficer;
import io.project.app.police.repositories.CurrentDutyAssignmentRepository;
import io.project.app.police.repositories.PoliceCarJpaRepository;
import io.project.app.police.repositories.PoliceOfficerJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author armena
 */
@Service
@Slf4j
public class CurrentDutyAssignmentService {

    @Autowired
    private CurrentDutyAssignmentRepository currentDutyAssignmentRepository;

    @Autowired
    private PoliceOfficerJpaRepository policeOfficerRepository;

    @Autowired
    private PoliceCarJpaRepository policeCarRepository;

    @Transactional(transactionManager = "transactionManager")
    public CurrentDutyAssignment createAssignment(Long officerId, Long carId, Double lat, Double lon) {

        PoliceOfficer officer = policeOfficerRepository.findById(officerId)
                .orElseThrow(() -> new EntityNotFoundException("PoliceOfficer with id " + officerId + " not found"));

        PoliceCar car = policeCarRepository.findById(carId)
                .orElseThrow(() -> new EntityNotFoundException("PoliceCar with id " + carId + " not found"));

        if (!car.isAvailable()) {
            throw new IllegalStateException("PoliceCar with id " + carId + " is not available for assignment");
        }

        CurrentDutyAssignment assignment = new CurrentDutyAssignment(car, lat, lon);
        assignment.getOfficer().add(officer);
        assignment = currentDutyAssignmentRepository.save(assignment);
        car.setAvailable(false);
        policeCarRepository.save(car);

        return assignment;
    }

    @Transactional(transactionManager = "transactionManager")
    public void deleteAssignmentAndMakeCarAvailable(Long assignmentId) {
        CurrentDutyAssignment assignment = currentDutyAssignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new EntityNotFoundException("CurrentDutyAssignment with id " + assignmentId + " not found"));

        assignment.getCar().setAvailable(true);
        policeCarRepository.save(assignment.getCar());
        currentDutyAssignmentRepository.delete(assignment);
    }

}
