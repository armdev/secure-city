package io.project.app.police.services;

import io.project.app.police.domain.CarLocation;
import io.project.app.police.domain.PoliceCar;
import io.project.app.police.domain.PoliceDuty;
import io.project.app.police.domain.PoliceOfficer;
import io.project.app.police.repositories.PoliceCarRepository;
import io.project.app.police.repositories.PoliceOfficerRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PoliceCarService {

    private final PoliceCarRepository policeCarRepository;
    private final PoliceOfficerRepository policeOfficerRepository;

    public PoliceCarService(PoliceCarRepository policeCarRepository, PoliceOfficerRepository policeOfficerRepository) {
        this.policeCarRepository = policeCarRepository;
        this.policeOfficerRepository = policeOfficerRepository;
    }

    public PoliceCar createNewPoliceCar(PoliceCar policeCar) {
        return policeCarRepository.save(policeCar);
    }

    public PoliceCar createNewDutyAndUpdateHistory(String policeCarId, String primaryOfficerId, String secondaryOfficerId) {
        PoliceCar policeCar = policeCarRepository.findById(policeCarId)
                .orElseThrow(() -> new RuntimeException("Police car not found with id: " + policeCarId));

        PoliceOfficer primaryOfficer = policeOfficerRepository.findById(primaryOfficerId)
                .orElseThrow(() -> new RuntimeException("Primary officer not found with id: " + primaryOfficerId));

        PoliceOfficer secondaryOfficer = policeOfficerRepository.findById(secondaryOfficerId)
                .orElseThrow(() -> new RuntimeException("Secondary officer not found with id: " + secondaryOfficerId));

        PoliceDuty newDuty = new PoliceDuty();
        newDuty.setPrimaryOfficer(primaryOfficer);
        newDuty.setSecondaryOfficer(secondaryOfficer);
        newDuty.setDutyDate(LocalDateTime.now());

        policeCar.setPoliceDuty(newDuty);
        policeCar.getDutyHistory().add(0, newDuty);

        return policeCarRepository.save(policeCar);
    }

    public PoliceCar changeOfficerInTheDutyAndUpdateHistory(String policeCarId, String existingOfficerId, String newOfficerId) {
        PoliceCar policeCar = policeCarRepository.findById(policeCarId)
                .orElseThrow(() -> new RuntimeException("Police car not found with id: " + policeCarId));

        PoliceDuty currentDuty = policeCar.getPoliceDuty();
        if (currentDuty == null) {
            throw new RuntimeException("No duty found for police car with id: " + policeCarId);
        }

        PoliceOfficer existingOfficer = currentDuty.getPrimaryOfficer().getId().equals(existingOfficerId)
                ? currentDuty.getPrimaryOfficer() : currentDuty.getSecondaryOfficer();
        if (existingOfficer == null) {
            throw new RuntimeException("Officer not found in duty with id: " + existingOfficerId);
        }

        PoliceOfficer newOfficer = policeOfficerRepository.findById(newOfficerId)
                .orElseThrow(() -> new RuntimeException("New officer not found with id: " + newOfficerId));

        if (existingOfficer.getId().equals(newOfficer.getId())) {
            return policeCar; // No change required
        }

        if (currentDuty.getPrimaryOfficer().getId().equals(existingOfficer.getId())) {
            currentDuty.setPrimaryOfficer(newOfficer);
        } else {
            currentDuty.setSecondaryOfficer(newOfficer);
        }

        policeCar.getDutyHistory().add(0, currentDuty);

        return policeCarRepository.save(policeCar);
    }

    public PoliceCar updateCurrentLocationAndHistory(String policeCarId, CarLocation carLocation) {
        PoliceCar policeCar = policeCarRepository.findById(policeCarId)
                .orElseThrow(() -> new RuntimeException("Police car not found with id: " + policeCarId));

        if (policeCar.getCurrentLocation() == null) {
            policeCar.setLocationHistory(new ArrayList<>());

        }

        policeCar.getLocationHistory().add(policeCar.getCurrentLocation()); // Add current location to history

        policeCar.setCurrentLocation(carLocation);
        policeCar.getCurrentLocation().setCheckinDate(LocalDateTime.now());

        return policeCarRepository.save(policeCar);

    }
    
     public void saveAll(List<PoliceCar> list) {
       

         policeCarRepository.saveAll(list);
    }
}
