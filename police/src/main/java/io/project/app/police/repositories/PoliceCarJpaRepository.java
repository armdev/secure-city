package io.project.app.police.repositories;

import io.project.app.police.domain.PoliceCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoliceCarJpaRepository extends JpaRepository<PoliceCar, Long> {

}
