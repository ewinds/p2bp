package io.github.ewinds.client;

import io.github.ewinds.service.dto.CustomerDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "uaa")
public interface CustomerServiceClient {
    @PostMapping("/api/customers/:ids")
    List<CustomerDTO> getCustomersByIds(@Valid @RequestBody List<Long> ids);
}
