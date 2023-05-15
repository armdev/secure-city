package io.project.app.police.duty.resources;

import io.project.app.police.domain.PoliceCar;
import io.project.app.police.helpers.ChangeDutyRequest;
import io.project.app.police.helpers.NewDutyRequest;
import io.project.app.police.helpers.PoliceCarCreationRequest;
import io.project.app.police.services.PoliceCarService;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author user
 */
@RestController
@RequestMapping("/api/police-cars-duty")
@Slf4j
public class PoliceDutyController {

    private final PoliceCarService policeCarService;

    public PoliceDutyController(PoliceCarService policeCarService) {
        this.policeCarService = policeCarService;
    }

    @PostMapping("/create/new/car")
    public ResponseEntity<PoliceCar> createNewPoliceCar(@RequestBody PoliceCarCreationRequest policeCar) {
        PoliceCar createdCar = policeCarService.createNewPoliceCar(policeCar);
        return ResponseEntity.ok(createdCar);
    }

    @PutMapping("/create/new/duty")
    public ResponseEntity<PoliceCar> createNewDutyAndUpdateHistory(@RequestBody NewDutyRequest newDutyRequest
    ) {
        Optional<PoliceCar> updatedCar = policeCarService.createNewDutyAndUpdateHistory(newDutyRequest.getPoliceCarId(), newDutyRequest.getPrimaryOfficerId(),
                newDutyRequest.getSecondaryOfficerId());

        if (!updatedCar.isPresent()) {
            log.error("Error Occured");
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(updatedCar.get());
    }

    @PutMapping("/change/officer/in/duty")
    public ResponseEntity<PoliceCar> changeOfficerInTheDutyAndUpdateHistory(@RequestBody ChangeDutyRequest changeDutyRequest) {
        PoliceCar updatedCar = policeCarService.changeOfficerInTheDutyAndUpdateHistory(changeDutyRequest.getPoliceCarId(), changeDutyRequest.getExistingOfficerId(), changeDutyRequest.getNewOfficerId());
        return ResponseEntity.ok(updatedCar);
    }

//    @PutMapping("/{policeCarId}/location")
//    public ResponseEntity<PoliceCar> updateCurrentLocationAndHistory(@PathVariable String policeCarId,
//            @RequestBody CarLocation carLocation) {
//        PoliceCar updatedCar = policeCarService.updateCurrentLocationAndHistory(policeCarId, carLocation);
//        return ResponseEntity.ok(updatedCar);
//    }
}
