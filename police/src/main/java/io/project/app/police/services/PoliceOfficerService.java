package io.project.app.police.services;

import io.project.app.police.domain.PoliceOfficer;
import io.project.app.police.repositories.PoliceOfficerRepository;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PoliceOfficerService {

    private final PoliceOfficerRepository policeOfficerRepository;

    public PoliceOfficerService(PoliceOfficerRepository policeOfficerRepository) {
        this.policeOfficerRepository = policeOfficerRepository;
    }

    public PoliceOfficer createNewPoliceOfficer(PoliceOfficer policeOfficer) {
        return policeOfficerRepository.save(policeOfficer);
    }

    public PoliceOfficer getPoliceOfficerById(String id) {
        return policeOfficerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Police officer not found with id: " + id));
    }

    public List<PoliceOfficer> getAllPoliceOfficers() {
        return policeOfficerRepository.findAll();
    }

    public PoliceOfficer updatePoliceOfficerById(String id, PoliceOfficer policeOfficer) {
        PoliceOfficer existingOfficer = getPoliceOfficerById(id);
        existingOfficer.setName(policeOfficer.getName());
        existingOfficer.setBadgeNumber(policeOfficer.getBadgeNumber());
        existingOfficer.setAge(policeOfficer.getAge());
        return policeOfficerRepository.save(existingOfficer);
    }

    public void deletePoliceOfficerById(String id) {
        PoliceOfficer existingOfficer = getPoliceOfficerById(id);
        policeOfficerRepository.delete(existingOfficer);
    }
    
     public void saveAll(List<PoliceOfficer> list) {
       

         policeOfficerRepository.saveAll(list);
    }
}
