package io.github.ewinds.web.rest;

import io.github.ewinds.config.ApplicationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Foo controller
 */
@RestController
@RequestMapping("/api/foo")
public class FooResource {

    private final Logger log = LoggerFactory.getLogger(FooResource.class);

    @Autowired
    ApplicationProperties applicationProperties;

    /**
    * GET list
    */
    @GetMapping("/list")
    public List<ApplicationProperties.KeyValue> list() {
        return applicationProperties.getServiceFees();
    }

}
