package io.github.ewinds.client;

import io.github.ewinds.security.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class UserServiceClientFallback implements UserServiceClient {

    private final Logger log = LoggerFactory.getLogger(UserServiceClientFallback.class);

    @Override
    public ResponseEntity<Void> register(UserDTO userDTO) {
        log.error("/uaa/api/register 调用失败 ");
        return null;
    }
}
