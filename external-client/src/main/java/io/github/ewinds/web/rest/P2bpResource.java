package io.github.ewinds.web.rest;

import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * P2bp controller
 */
@RestController
@RequestMapping("/api")
public class P2bpResource {

    private final Logger log = LoggerFactory.getLogger(P2bpResource.class);

    private final OAuth2RestTemplate oAuth2RestTemplate;

    public P2bpResource(OAuth2RestTemplate oAuth2RestTemplate) {
        this.oAuth2RestTemplate = oAuth2RestTemplate;
    }

    /**
    * GET defaultAction
    */
    @GetMapping("/ping")
    @Timed
    public String ping() {
        return oAuth2RestTemplate.getForObject("http://localhost:8080/api/gateway/ping", String.class);
//        return "pong";
    }
}
