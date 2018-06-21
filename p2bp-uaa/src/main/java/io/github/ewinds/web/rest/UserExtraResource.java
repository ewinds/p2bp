package io.github.ewinds.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.ewinds.domain.UserExtra;

import io.github.ewinds.repository.UserExtraRepository;
import io.github.ewinds.web.rest.errors.BadRequestAlertException;
import io.github.ewinds.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing UserExtra.
 */
@RestController
@RequestMapping("/api")
public class UserExtraResource {

    private final Logger log = LoggerFactory.getLogger(UserExtraResource.class);

    private static final String ENTITY_NAME = "userExtra";

    private final UserExtraRepository userExtraRepository;

    public UserExtraResource(UserExtraRepository userExtraRepository) {
        this.userExtraRepository = userExtraRepository;
    }

    /**
     * POST  /user-extras : Create a new userExtra.
     *
     * @param userExtra the userExtra to create
     * @return the ResponseEntity with status 201 (Created) and with body the new userExtra, or with status 400 (Bad Request) if the userExtra has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/user-extras")
    @Timed
    public ResponseEntity<UserExtra> createUserExtra(@RequestBody UserExtra userExtra) throws URISyntaxException {
        log.debug("REST request to save UserExtra : {}", userExtra);
        if (userExtra.getId() != null) {
            throw new BadRequestAlertException("A new userExtra cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserExtra result = userExtraRepository.save(userExtra);
        return ResponseEntity.created(new URI("/api/user-extras/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /user-extras : Updates an existing userExtra.
     *
     * @param userExtra the userExtra to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated userExtra,
     * or with status 400 (Bad Request) if the userExtra is not valid,
     * or with status 500 (Internal Server Error) if the userExtra couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/user-extras")
    @Timed
    public ResponseEntity<UserExtra> updateUserExtra(@RequestBody UserExtra userExtra) throws URISyntaxException {
        log.debug("REST request to update UserExtra : {}", userExtra);
        if (userExtra.getId() == null) {
            return createUserExtra(userExtra);
        }
        UserExtra result = userExtraRepository.save(userExtra);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, userExtra.getId().toString()))
            .body(result);
    }

    /**
     * GET  /user-extras : get all the userExtras.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of userExtras in body
     */
    @GetMapping("/user-extras")
    @Timed
    public List<UserExtra> getAllUserExtras() {
        log.debug("REST request to get all UserExtras");
        return userExtraRepository.findAll();
        }

    /**
     * GET  /user-extras/:id : get the "id" userExtra.
     *
     * @param id the id of the userExtra to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the userExtra, or with status 404 (Not Found)
     */
    @GetMapping("/user-extras/{id}")
    @Timed
    public ResponseEntity<UserExtra> getUserExtra(@PathVariable Long id) {
        log.debug("REST request to get UserExtra : {}", id);
        UserExtra userExtra = userExtraRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(userExtra));
    }

    /**
     * DELETE  /user-extras/:id : delete the "id" userExtra.
     *
     * @param id the id of the userExtra to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/user-extras/{id}")
    @Timed
    public ResponseEntity<Void> deleteUserExtra(@PathVariable Long id) {
        log.debug("REST request to delete UserExtra : {}", id);
        userExtraRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
