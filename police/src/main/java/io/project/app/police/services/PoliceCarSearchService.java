package io.project.app.police.services;

import io.project.app.police.domain.PoliceCar;
import io.project.app.police.repositories.PoliceCarRepository;
import io.project.app.police.repositories.PoliceOfficerRepository;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PoliceCarSearchService {

    private final PoliceCarRepository policeCarRepository;
    private final PoliceOfficerRepository policeOfficerRepository;

    public PoliceCarSearchService(PoliceCarRepository policeCarRepository, PoliceOfficerRepository policeOfficerRepository) {
        this.policeCarRepository = policeCarRepository;
        this.policeOfficerRepository = policeOfficerRepository;
    }

    public List<PoliceCar> findTop10ByAvailableIsTrue() {
        return policeCarRepository.findTop10ByAvailableIsTrue();
    }

  
}
