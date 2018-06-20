package io.github.ewinds.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.ewinds.domain.OriginEnterprise;

import io.github.ewinds.repository.OriginEnterpriseRepository;
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
 * REST controller for managing OriginEnterprise.
 */
@RestController
@RequestMapping("/api")
public class OriginEnterpriseResource {

    private final Logger log = LoggerFactory.getLogger(OriginEnterpriseResource.class);

    private static final String ENTITY_NAME = "originEnterprise";

    private final OriginEnterpriseRepository originEnterpriseRepository;

    public OriginEnterpriseResource(OriginEnterpriseRepository originEnterpriseRepository) {
        this.originEnterpriseRepository = originEnterpriseRepository;
    }

    /**
     * POST  /origin-enterprises : Create a new originEnterprise.
     *
     * @param originEnterprise the originEnterprise to create
     * @return the ResponseEntity with status 201 (Created) and with body the new originEnterprise, or with status 400 (Bad Request) if the originEnterprise has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/origin-enterprises")
    @Timed
    public ResponseEntity<OriginEnterprise> createOriginEnterprise(@Valid @RequestBody OriginEnterprise originEnterprise) throws URISyntaxException {
        log.debug("REST request to save OriginEnterprise : {}", originEnterprise);
        if (originEnterprise.getId() != null) {
            throw new BadRequestAlertException("A new originEnterprise cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OriginEnterprise result = originEnterpriseRepository.save(originEnterprise);
        return ResponseEntity.created(new URI("/api/origin-enterprises/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /origin-enterprises : Updates an existing originEnterprise.
     *
     * @param originEnterprise the originEnterprise to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated originEnterprise,
     * or with status 400 (Bad Request) if the originEnterprise is not valid,
     * or with status 500 (Internal Server Error) if the originEnterprise couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/origin-enterprises")
    @Timed
    public ResponseEntity<OriginEnterprise> updateOriginEnterprise(@Valid @RequestBody OriginEnterprise originEnterprise) throws URISyntaxException {
        log.debug("REST request to update OriginEnterprise : {}", originEnterprise);
        if (originEnterprise.getId() == null) {
            return createOriginEnterprise(originEnterprise);
        }
        OriginEnterprise result = originEnterpriseRepository.save(originEnterprise);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, originEnterprise.getId().toString()))
            .body(result);
    }

    /**
     * GET  /origin-enterprises : get all the originEnterprises.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of originEnterprises in body
     */
    @GetMapping("/origin-enterprises")
    @Timed
    public List<OriginEnterprise> getAllOriginEnterprises() {
        log.debug("REST request to get all OriginEnterprises");
        return originEnterpriseRepository.findAll();
        }

    /**
     * GET  /origin-enterprises/:id : get the "id" originEnterprise.
     *
     * @param id the id of the originEnterprise to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the originEnterprise, or with status 404 (Not Found)
     */
    @GetMapping("/origin-enterprises/{id}")
    @Timed
    public ResponseEntity<OriginEnterprise> getOriginEnterprise(@PathVariable Long id) {
        log.debug("REST request to get OriginEnterprise : {}", id);
        OriginEnterprise originEnterprise = originEnterpriseRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(originEnterprise));
    }

    /**
     * DELETE  /origin-enterprises/:id : delete the "id" originEnterprise.
     *
     * @param id the id of the originEnterprise to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/origin-enterprises/{id}")
    @Timed
    public ResponseEntity<Void> deleteOriginEnterprise(@PathVariable Long id) {
        log.debug("REST request to delete OriginEnterprise : {}", id);
        originEnterpriseRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
