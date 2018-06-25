package io.github.ewinds.web.rest;

import io.github.ewinds.repository.UserRepository;
import io.github.ewinds.service.dto.CustomerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Customer controller
 */
@RestController
@RequestMapping("/api")
public class CustomerResource {

    private final Logger log = LoggerFactory.getLogger(CustomerResource.class);

    private final UserRepository userRepository;

    public CustomerResource(UserRepository userRepository) {

        this.userRepository = userRepository;
    }
    /**
    * POST getCustomersByIds
    */
    @PostMapping("/customers/:ids")
    public List<CustomerDTO> getCustomersByIds(@Valid @RequestBody List<Long> ids) {
        return userRepository.findByIdIn(ids).stream().map(CustomerDTO::new).collect(Collectors.toList());
    }

}
