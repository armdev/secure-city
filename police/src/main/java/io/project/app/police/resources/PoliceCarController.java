package io.project.app.police.resources;

import io.project.app.police.domain.PoliceCar;
import io.project.app.police.services.PoliceCarSearchService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author user
 */
@RestController
@RequestMapping("/api/police-cars")
public class PoliceCarController {

    private final PoliceCarSearchService policeCarSearchService;

    public PoliceCarController(PoliceCarSearchService policeCarSearchService) {
        this.policeCarSearchService = policeCarSearchService;
    }

    @GetMapping("/available")
    public ResponseEntity<?> findTop() {
        List<PoliceCar> findTop10ByAvailableIsTrue = policeCarSearchService.findTop10ByAvailableIsTrue();
        return ResponseEntity.ok(findTop10ByAvailableIsTrue);
    }

    
}
