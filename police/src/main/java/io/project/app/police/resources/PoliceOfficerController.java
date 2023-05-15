package io.project.app.police.resources;

import io.project.app.police.domain.PoliceCar;
import io.project.app.police.domain.PoliceOfficer;
import io.project.app.police.errors.OfficerNotFoundException;
import io.project.app.police.services.PoliceOfficerSearchService;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author user
 */
@RestController
@RequestMapping("/api/police-officers")
public class PoliceOfficerController {

    private final PoliceOfficerSearchService policeOfficerSearchService;

    public PoliceOfficerController(PoliceOfficerSearchService policeOfficerSearchService) {
        this.policeOfficerSearchService = policeOfficerSearchService;
    }

    @GetMapping("/available")
    public ResponseEntity<?> findTop() {
        List<PoliceOfficer> findTopOfficer = policeOfficerSearchService.findTopOfficer();
        return ResponseEntity.ok(findTopOfficer);
    }

    @GetMapping("/find/officer")
//    public ResponseEntity<?> findOfficer(@RequestParam String officerId) {
//        PoliceCar policeCar = policeOfficerSearchService.findOfficerInDuty(officerId)
//                .orElseThrow(() -> new OfficerNotFoundException("Officer not found"));
//        return ResponseEntity.ok(policeCar);
//    }

    public ResponseEntity<?> findOfficer(@RequestParam String officerId) {
        Optional<PoliceCar> policeCar = policeOfficerSearchService.findOfficerInDuty(officerId);
        return policeCar.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

}
