package io.github.ewinds.repository;

import io.github.ewinds.domain.OriginIndividual;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the OriginIndividual entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OriginIndividualRepository extends JpaRepository<OriginIndividual, Long> {

}
