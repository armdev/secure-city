package io.project.app.police.resources;

import io.project.app.police.domain.CurrentDutyAssignment;
import io.project.app.police.incident.DutyAssignmentDto;
import io.project.app.police.services.CurrentDutyAssignmentService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author armena
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class AssignmentController {

    @Autowired
    private CurrentDutyAssignmentService currentDutyAssignmentService;

    @PostMapping("/assignments")
    public ResponseEntity<?> createAssignment(
            @RequestParam Long officerId,
            @RequestParam Long carId,
            @RequestParam Double lat,
            @RequestParam Double lon) {
        try {
            CurrentDutyAssignment assignment = currentDutyAssignmentService.createAssignment(officerId, carId, lat, lon);
            return ResponseEntity.ok(assignment);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/assignments/{id}")
    public ResponseEntity<?> unassign(@PathVariable Long id) {
        try {
            currentDutyAssignmentService.deleteAssignmentAndMakeCarAvailable(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/assignments/info")
    public ResponseEntity<?> info() {
        List<DutyAssignmentDto> info = currentDutyAssignmentService.getInfo();
        return ResponseEntity.ok().body(info);

    }

}
