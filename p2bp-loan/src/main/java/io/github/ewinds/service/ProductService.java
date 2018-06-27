package io.github.ewinds.service;

import io.github.ewinds.domain.Product;
import io.github.ewinds.domain.enumeration.InterestCalculation;
import io.github.ewinds.domain.enumeration.ProductState;
import io.github.ewinds.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Service class for managing users.
 */
@Service
@Transactional
public class ProductService {

    private final Logger log = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Not activated users should be automatically deleted after 3 days.
     * <p>
     * This is scheduled to get fired every hour.
     */
    @Scheduled(cron = "0 0 * * * ?")
    public void failExpiredProduct() {
        List<Product> fails = productRepository.findAllFailed(Instant.now().minus(12, ChronoUnit.HOURS), ProductState.PUBLISHED, InterestCalculation.RATIO);

        // 更新融资产品
        for (Product product : fails) {
            log.debug("Failing expired product {}", product.getId());
            product.setState(ProductState.FAILED);
        }
        productRepository.save(fails);

        // 更新用户投资

        // 筹款期限过期通知
    }
}
