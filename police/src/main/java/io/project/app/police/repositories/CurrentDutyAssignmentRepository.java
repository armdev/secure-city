package io.project.app.police.repositories;

import io.project.app.police.domain.CurrentDutyAssignment;
import io.project.app.police.incident.DutyAssignmentDto;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrentDutyAssignmentRepository extends JpaRepository<CurrentDutyAssignment, Long> {

//    @Query("SELECT new io.project.app.police.incident.DutyAssignmentDto(a.id, c.make, c.model, c.carNumber, a.lat, a.lon, "
//            + " o.id, o.name) "
//            + "FROM CurrentDutyAssignment a "
//            + "JOIN a.car c "
//            + "JOIN a.officer o")
//    List<DutyAssignmentDto> findAllAssignmentsWithCarAndOfficers();
    
    @Query("SELECT NEW io.project.app.police.incident.DutyAssignmentDto(a.id, c.id, c.carNumber, o.id, o.name) "
            + "FROM CurrentDutyAssignment a "
            + "JOIN a.car c "
            + "JOIN a.officer o")
    List<DutyAssignmentDto> findAllBy();

}
