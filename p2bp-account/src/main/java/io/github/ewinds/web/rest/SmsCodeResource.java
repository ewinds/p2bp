package io.github.ewinds.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.ewinds.domain.SmsCode;
import io.github.ewinds.service.SmsCodeService;
import io.github.ewinds.web.rest.errors.BadRequestAlertException;
import io.github.ewinds.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.Optional;

/**
 * REST controller for managing SmsCode.
 */
@RestController
@RequestMapping("/api")
public class SmsCodeResource {

    private final Logger log = LoggerFactory.getLogger(SmsCodeResource.class);

    private static final String ENTITY_NAME = "smsCode";

    private final SmsCodeService smsCodeService;

    public SmsCodeResource(SmsCodeService smsCodeService) {
        this.smsCodeService = smsCodeService;
    }

    /**
     * POST  /sms-codes : Create a new smsCode.
     *
     * @param smsCode the smsCode to create
     * @return the ResponseEntity with status 201 (Created) and with body the new smsCode, or with status 400 (Bad Request) if the smsCode has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sms-codes")
    @Timed
    public ResponseEntity<Void> createSmsCode(@RequestBody SmsCode smsCode) throws URISyntaxException {
        log.debug("REST request to save SmsCode : {}", smsCode);
        if (smsCode.getId() != null) {
            throw new BadRequestAlertException("A new smsCode cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SmsCode result = smsCodeService.save(smsCode);
        return ResponseEntity.created(new URI("/api/sms-codes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .build();
    }

    /**
     * POST  /sms-codes:verify : Verify an existing smsCode.
     *
     * @param smsCode the smsCode to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated smsCode,
     * or with status 400 (Bad Request) if the smsCode is not valid,
     * or with status 500 (Internal Server Error) if the smsCode couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sms-codes:verify")
    @Timed
    public ResponseEntity<Void> verifySmsCode(@RequestBody SmsCode smsCode) throws URISyntaxException {
        log.debug("REST request to verify SmsCode : {}", smsCode);

        if (smsCode.getCode() == null) {
            throw new BadRequestAlertException("Verify smsCode must have code", ENTITY_NAME, "required");
        }

        Optional<SmsCode> result = smsCodeService.findValidOne(smsCode.getPhone(), smsCode.getCode());

        if (!result.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok()
            .headers(HeaderUtil.createAlert("Verify smsCode ok", smsCode.getPhone()))
            .build();
    }
}
