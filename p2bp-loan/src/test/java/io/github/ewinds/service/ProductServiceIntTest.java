package io.github.ewinds.service;

import io.github.ewinds.LoanApp;
import io.github.ewinds.domain.Product;
import io.github.ewinds.domain.enumeration.InterestCalculation;
import io.github.ewinds.domain.enumeration.InterestCalculationPeriod;
import io.github.ewinds.domain.enumeration.ProductState;
import io.github.ewinds.repository.ProductRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LoanApp.class)
@Transactional
public class ProductServiceIntTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    private Product product;

    @Before
    public void init() {
        product = new Product();
        product.setTitle("AAAAA");
        product.setState(ProductState.APPROVED);
        product.setAmount(BigDecimal.valueOf(50000));
        product.setInterestCalculation(InterestCalculation.AUTO);
        product.setInterestRate(BigDecimal.valueOf(12));
        product.setPeriodType(InterestCalculationPeriod.MONTH);
        product.setPeriod(12);
        product.setStartDate(Instant.now());
    }

    @Test
    @Transactional
    public void testFailExpiredProduct() {
        product.setEndDate(Instant.now().minus(30, ChronoUnit.DAYS));
        productRepository.saveAndFlush(product);

        assertThat(productRepository.findOne(product.getId()).getState()).isEqualByComparingTo(ProductState.APPROVED);
        productService.failExpiredProduct();
        assertThat(productRepository.findOne(product.getId()).getState()).isEqualByComparingTo(ProductState.FAILED);
    }
}
