package io.github.ewinds.repository;

import io.github.ewinds.domain.Product;
import io.github.ewinds.domain.enumeration.InterestCalculation;
import io.github.ewinds.domain.enumeration.ProductState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;


/**
 * Spring Data JPA repository for the Product entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select product from Product product " +
        "where product.state = :state and product.endDate < :deadline " +
        "and ((product.fulfilled = false and product.partialAllowed = false and product.interestCalculation <> :ratio)" +
        "or (product.interestCalculation = :ratio and product.amountTendered < (product.amount*product.interestCalculationRatio/100)))")
    List<Product> findAllFailed(@Param("deadline") Instant deadline, @Param("state") ProductState state, @Param("ratio") InterestCalculation ratio);
}
