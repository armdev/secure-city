package io.project.app.police.services;

import io.project.app.police.domain.CurrentDutyAssignment;
import io.project.app.police.domain.PoliceCar;
import io.project.app.police.domain.PoliceOfficer;
import io.project.app.police.repositories.CurrentDutyAssignmentRepository;
import io.project.app.police.repositories.PoliceCarJpaRepository;
import io.project.app.police.repositories.PoliceOfficerJpaRepository;
import java.util.List;
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

    @Transactional(transactionManager = "transactionManager")
    public void generateDutyAssignments() {
        List<PoliceCar> availableCars = policeCarRepository.findTop50ByAvailableIsTrueOrderByMakeAscModelAscYearAsc();
        List<PoliceOfficer> officers = policeOfficerRepository.findTop100ByOrderByAgeAsc();

        int numAssignments = Math.min(availableCars.size() * 2, officers.size());
        for (int i = 0; i < numAssignments; i += 2) {
            PoliceCar car = availableCars.get(i / 2);
            PoliceOfficer officer1 = officers.get(i);
            PoliceOfficer officer2 = officers.get(i + 1);

            CurrentDutyAssignment assignment1 = new CurrentDutyAssignment(officer1, car, null, null);
            currentDutyAssignmentRepository.save(assignment1);

            CurrentDutyAssignment assignment2 = new CurrentDutyAssignment(officer2, car, null, null);
            currentDutyAssignmentRepository.save(assignment2);

            car.setAvailable(false);
            policeCarRepository.save(car);
        }
    }
}
