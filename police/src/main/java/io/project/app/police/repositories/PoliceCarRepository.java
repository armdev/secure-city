package io.project.app.police.repositories;


import io.project.app.police.domain.PoliceCar;
import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;

/**
 *
 * @author armena
 */
@Repository
public interface PoliceCarRepository extends MongoRepository<PoliceCar, String> {

}
