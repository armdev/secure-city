package io.project.app.police.repositories;

import io.project.app.police.domain.PoliceCar;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoliceCarJpaRepository extends JpaRepository<PoliceCar, Long> {

    List<PoliceCar> findTop50ByAvailableIsTrueOrderByMakeAscModelAscYearAsc();
}
