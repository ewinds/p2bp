package io.github.ewinds.client;

import io.github.ewinds.security.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@AuthorizedFeignClient(name = "uaa")
public interface UserServiceClient {
    @PostMapping("/api/register")
    ResponseEntity<Void> register(@Valid @RequestBody UserDTO userDTO);
}
