package io.github.ewinds.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.ewinds.domain.ProductSpec;

import io.github.ewinds.repository.ProductSpecRepository;
import io.github.ewinds.web.rest.errors.BadRequestAlertException;
import io.github.ewinds.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing ProductSpec.
 */
@RestController
@RequestMapping("/api")
public class ProductSpecResource {

    private final Logger log = LoggerFactory.getLogger(ProductSpecResource.class);

    private static final String ENTITY_NAME = "productSpec";

    private final ProductSpecRepository productSpecRepository;

    public ProductSpecResource(ProductSpecRepository productSpecRepository) {
        this.productSpecRepository = productSpecRepository;
    }

    /**
     * POST  /product-specs : Create a new productSpec.
     *
     * @param productSpec the productSpec to create
     * @return the ResponseEntity with status 201 (Created) and with body the new productSpec, or with status 400 (Bad Request) if the productSpec has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/product-specs")
    @Timed
    public ResponseEntity<ProductSpec> createProductSpec(@Valid @RequestBody ProductSpec productSpec) throws URISyntaxException {
        log.debug("REST request to save ProductSpec : {}", productSpec);
        if (productSpec.getId() != null) {
            throw new BadRequestAlertException("A new productSpec cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProductSpec result = productSpecRepository.save(productSpec);
        return ResponseEntity.created(new URI("/api/product-specs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /product-specs : Updates an existing productSpec.
     *
     * @param productSpec the productSpec to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated productSpec,
     * or with status 400 (Bad Request) if the productSpec is not valid,
     * or with status 500 (Internal Server Error) if the productSpec couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/product-specs")
    @Timed
    public ResponseEntity<ProductSpec> updateProductSpec(@Valid @RequestBody ProductSpec productSpec) throws URISyntaxException {
        log.debug("REST request to update ProductSpec : {}", productSpec);
        if (productSpec.getId() == null) {
            return createProductSpec(productSpec);
        }
        ProductSpec result = productSpecRepository.save(productSpec);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, productSpec.getId().toString()))
            .body(result);
    }

    /**
     * GET  /product-specs : get all the productSpecs.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of productSpecs in body
     */
    @GetMapping("/product-specs")
    @Timed
    public List<ProductSpec> getAllProductSpecs() {
        log.debug("REST request to get all ProductSpecs");
        return productSpecRepository.findAll();
        }

    /**
     * GET  /product-specs/:id : get the "id" productSpec.
     *
     * @param id the id of the productSpec to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the productSpec, or with status 404 (Not Found)
     */
    @GetMapping("/product-specs/{id}")
    @Timed
    public ResponseEntity<ProductSpec> getProductSpec(@PathVariable Long id) {
        log.debug("REST request to get ProductSpec : {}", id);
        ProductSpec productSpec = productSpecRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(productSpec));
    }

    /**
     * DELETE  /product-specs/:id : delete the "id" productSpec.
     *
     * @param id the id of the productSpec to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/product-specs/{id}")
    @Timed
    public ResponseEntity<Void> deleteProductSpec(@PathVariable Long id) {
        log.debug("REST request to delete ProductSpec : {}", id);
        productSpecRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
