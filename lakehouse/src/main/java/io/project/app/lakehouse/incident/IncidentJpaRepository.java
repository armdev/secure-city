package io.project.app.lakehouse.incident;


import io.project.app.lakehouse.domain.IncidentData;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidentJpaRepository extends JpaRepository<IncidentData, Long> {


}
