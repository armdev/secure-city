package io.project.app.police.repositories;


import io.project.app.police.domain.PoliceOfficer;
import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;

/**
 *
 * @author armena
 */
@Repository
public interface PoliceOfficerRepository extends MongoRepository<PoliceOfficer, String> {

}
