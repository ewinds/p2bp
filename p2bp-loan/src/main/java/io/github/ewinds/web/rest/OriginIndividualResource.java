package io.github.ewinds.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.ewinds.domain.OriginIndividual;

import io.github.ewinds.repository.OriginIndividualRepository;
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
 * REST controller for managing OriginIndividual.
 */
@RestController
@RequestMapping("/api")
public class OriginIndividualResource {

    private final Logger log = LoggerFactory.getLogger(OriginIndividualResource.class);

    private static final String ENTITY_NAME = "originIndividual";

    private final OriginIndividualRepository originIndividualRepository;

    public OriginIndividualResource(OriginIndividualRepository originIndividualRepository) {
        this.originIndividualRepository = originIndividualRepository;
    }

    /**
     * POST  /origin-individuals : Create a new originIndividual.
     *
     * @param originIndividual the originIndividual to create
     * @return the ResponseEntity with status 201 (Created) and with body the new originIndividual, or with status 400 (Bad Request) if the originIndividual has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/origin-individuals")
    @Timed
    public ResponseEntity<OriginIndividual> createOriginIndividual(@Valid @RequestBody OriginIndividual originIndividual) throws URISyntaxException {
        log.debug("REST request to save OriginIndividual : {}", originIndividual);
        if (originIndividual.getId() != null) {
            throw new BadRequestAlertException("A new originIndividual cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OriginIndividual result = originIndividualRepository.save(originIndividual);
        return ResponseEntity.created(new URI("/api/origin-individuals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /origin-individuals : Updates an existing originIndividual.
     *
     * @param originIndividual the originIndividual to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated originIndividual,
     * or with status 400 (Bad Request) if the originIndividual is not valid,
     * or with status 500 (Internal Server Error) if the originIndividual couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/origin-individuals")
    @Timed
    public ResponseEntity<OriginIndividual> updateOriginIndividual(@Valid @RequestBody OriginIndividual originIndividual) throws URISyntaxException {
        log.debug("REST request to update OriginIndividual : {}", originIndividual);
        if (originIndividual.getId() == null) {
            return createOriginIndividual(originIndividual);
        }
        OriginIndividual result = originIndividualRepository.save(originIndividual);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, originIndividual.getId().toString()))
            .body(result);
    }

    /**
     * GET  /origin-individuals : get all the originIndividuals.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of originIndividuals in body
     */
    @GetMapping("/origin-individuals")
    @Timed
    public List<OriginIndividual> getAllOriginIndividuals() {
        log.debug("REST request to get all OriginIndividuals");
        return originIndividualRepository.findAll();
        }

    /**
     * GET  /origin-individuals/:id : get the "id" originIndividual.
     *
     * @param id the id of the originIndividual to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the originIndividual, or with status 404 (Not Found)
     */
    @GetMapping("/origin-individuals/{id}")
    @Timed
    public ResponseEntity<OriginIndividual> getOriginIndividual(@PathVariable Long id) {
        log.debug("REST request to get OriginIndividual : {}", id);
        OriginIndividual originIndividual = originIndividualRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(originIndividual));
    }

    /**
     * DELETE  /origin-individuals/:id : delete the "id" originIndividual.
     *
     * @param id the id of the originIndividual to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/origin-individuals/{id}")
    @Timed
    public ResponseEntity<Void> deleteOriginIndividual(@PathVariable Long id) {
        log.debug("REST request to delete OriginIndividual : {}", id);
        originIndividualRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
