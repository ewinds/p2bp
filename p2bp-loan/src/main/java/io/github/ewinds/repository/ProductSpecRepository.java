package io.github.ewinds.repository;

import io.github.ewinds.domain.ProductSpec;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the ProductSpec entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductSpecRepository extends JpaRepository<ProductSpec, Long> {

}
