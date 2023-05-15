package io.project.app.police.services;

import io.project.app.police.domain.PoliceCar;
import io.project.app.police.domain.PoliceOfficer;
import io.project.app.police.repositories.PoliceCarRepository;
import io.project.app.police.repositories.PoliceOfficerRepository;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PoliceOfficerSearchService {

    private final PoliceCarRepository policeCarRepository;
    private final PoliceOfficerRepository policeOfficerRepository;

    public PoliceOfficerSearchService(PoliceCarRepository policeCarRepository, PoliceOfficerRepository policeOfficerRepository) {
        this.policeCarRepository = policeCarRepository;
        this.policeOfficerRepository = policeOfficerRepository;
    }

    public Optional<PoliceCar> findOfficerInDuty(String officerId) {
        return policeCarRepository.findByPoliceDutyPrimaryOfficerIdOrPoliceDutySecondaryOfficerId(officerId, officerId);
    }

    public List<PoliceOfficer> findTopOfficer() {
        return policeOfficerRepository.findTop10ByOrderByIdDesc();
    }

}
