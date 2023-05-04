package io.project.app.police.services;

import io.project.app.police.domain.CurrentDutyAssignment;
import io.project.app.police.domain.PoliceCar;
import io.project.app.police.domain.PoliceOfficer;
import io.project.app.police.repositories.CurrentDutyAssignmentRepository;
import io.project.app.police.repositories.PoliceCarJpaRepository;
import io.project.app.police.repositories.PoliceOfficerJpaRepository;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
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
public class DutyAssignmentGeneratorService {

    @Autowired
    private PoliceOfficerJpaRepository policeOfficerRepository;

    @Autowired
    private PoliceCarJpaRepository policeCarRepository;

    @Autowired
    private CurrentDutyAssignmentRepository currentDutyAssignmentRepository;

    private static final double BERLIN_LATITUDE = 52.5200;
    private static final double BERLIN_LONGITUDE = 13.4050;
    private static final double MAX_DISTANCE_KM = 5.0;
    private static final double EARTH_RADIUS_KM = 6371.0;

    @Transactional(transactionManager = "transactionManager")
    public void generateDutyAssignments() {
        List<PoliceCar> availableCars = policeCarRepository.findTop50ByAvailableIsTrueOrderByMakeAscModelAscYearAsc();
        List<PoliceOfficer> officers = policeOfficerRepository.findTop100ByOrderByAgeAsc();

        int numAssignments = Math.min(availableCars.size() * 2, officers.size());
        for (int i = 0; i < numAssignments; i += 2) {
            PoliceCar car = availableCars.get(i / 2);
            PoliceOfficer officer1 = officers.get(i);
            PoliceOfficer officer2 = officers.get(i + 1);

            double lat = BERLIN_LATITUDE + ThreadLocalRandom.current().nextDouble(-MAX_DISTANCE_KM, MAX_DISTANCE_KM) / EARTH_RADIUS_KM * (180 / Math.PI);
            double lon = BERLIN_LONGITUDE + ThreadLocalRandom.current().nextDouble(-MAX_DISTANCE_KM, MAX_DISTANCE_KM) / EARTH_RADIUS_KM * (180 / Math.PI) / Math.cos(BERLIN_LATITUDE * Math.PI / 180);

            CurrentDutyAssignment assignment1 = new CurrentDutyAssignment(car, lat, lon);
            assignment1.getOfficer().add(officer1);
            assignment1.getOfficer().add(officer2);            
            currentDutyAssignmentRepository.save(assignment1);

          

            car.setAvailable(false);
            policeCarRepository.save(car);
        }
    }
}
