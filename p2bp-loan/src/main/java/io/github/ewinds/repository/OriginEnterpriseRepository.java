package io.github.ewinds.repository;

import io.github.ewinds.domain.OriginEnterprise;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the OriginEnterprise entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OriginEnterpriseRepository extends JpaRepository<OriginEnterprise, Long> {

}
