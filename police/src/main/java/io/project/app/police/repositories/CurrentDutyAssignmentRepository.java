package io.project.app.police.repositories;

import io.project.app.police.domain.CurrentDutyAssignment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrentDutyAssignmentRepository extends JpaRepository<CurrentDutyAssignment, Long> {

}
