package io.project.app.police.repositories;

import io.project.app.police.domain.PoliceOfficer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoliceOfficerJpaRepository extends JpaRepository<PoliceOfficer, Long> {

    List<PoliceOfficer> findTop100ByOrderByAgeAsc();

}
