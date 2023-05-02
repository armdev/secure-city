package io.project.app.lakehouse.repositories;


import io.project.app.lakehouse.domain.IncidentData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidentJpaRepository extends JpaRepository<IncidentData, Long> {
    
}
