package io.project.app.police.officer.resources;

import io.project.app.police.domain.PoliceCar;
import io.project.app.police.domain.PoliceOfficer;
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
@RequestMapping("/api/police")
public class PoliceOfficerController {

    private final PoliceOfficerSearchService policeOfficerSearchService;

    public PoliceOfficerController(PoliceOfficerSearchService policeOfficerSearchService) {
        this.policeOfficerSearchService = policeOfficerSearchService;
    }

    @GetMapping("/find/top/officers")
    public ResponseEntity<?> findTop() {
        List<PoliceOfficer> findTopOfficer = policeOfficerSearchService.findTopOfficer();
        return ResponseEntity.ok(findTopOfficer);
    }

    @GetMapping("/find/officer/in/duty")
//    public ResponseEntity<?> findOfficer(@RequestParam String officerId) {
//        PoliceCar policeCar = policeOfficerSearchService.findOfficerInDuty(officerId)
//                .orElseThrow(() -> new OfficerNotFoundException("Officer not found"));
//        return ResponseEntity.ok(policeCar);
//    }

    public ResponseEntity<?> findOfficerInDuty(@RequestParam String officerId) {
        Optional<PoliceCar> policeCar = policeOfficerSearchService.findOfficerInDuty(officerId);
        return policeCar.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

}
