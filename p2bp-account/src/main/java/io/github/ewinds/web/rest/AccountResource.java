package io.github.ewinds.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import io.github.ewinds.client.UserServiceClient;
import io.github.ewinds.domain.SmsCode;
import io.github.ewinds.messaging.ProducerChannel;
import io.github.ewinds.messaging.RegisteredUser;
import io.github.ewinds.service.dto.UserDTO;
import io.github.ewinds.service.SmsCodeService;
import io.github.ewinds.web.rest.errors.BadRequestAlertException;
import io.github.ewinds.web.rest.errors.InvalidSmsCodeException;
import io.github.ewinds.web.rest.vm.PhoneLoginVM;
import io.github.ewinds.web.rest.vm.PhoneRegisterVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * Account controller
 */
@RestController
@RequestMapping("/api")
public class AccountResource {

    private final Logger log = LoggerFactory.getLogger(AccountResource.class);

    private final SmsCodeService smsCodeService;

    private final UserServiceClient userServiceClient;

    private MessageChannel channel;

    public AccountResource(SmsCodeService smsCodeService, UserServiceClient userServiceClient, ProducerChannel channel) {
        this.smsCodeService = smsCodeService;
        this.userServiceClient = userServiceClient;
        this.channel = channel.userChannel();
    }

    /**
     * POST /register : register the user.
     */
    @PostMapping("/register")
    @Timed
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@Valid @RequestBody PhoneRegisterVM phoneRegisterVM) {
        Optional<SmsCode> result = smsCodeService.findValidOne(phoneRegisterVM.getPhone(), phoneRegisterVM.getSmsCode());

        if (!result.isPresent()) {
            throw new InvalidSmsCodeException();
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setLogin(phoneRegisterVM.getPhone());
        userDTO.setPhone(phoneRegisterVM.getPhone());
        userDTO.setPassword(phoneRegisterVM.getPassword());

        try {
            userServiceClient.register(userDTO);
            channel.send(MessageBuilder.withPayload(new RegisteredUser(phoneRegisterVM.getPhone(), phoneRegisterVM.getPhone())).build());
        } catch (HystrixBadRequestException e) {
            throw new BadRequestAlertException(e.getMessage(), "account", "badregister");
        }
    }

    /**
     * POST /login : login the user.
     */
    @PostMapping("/login:phone")
    @Timed
    @ResponseStatus(HttpStatus.CREATED)
    public void loginByPhone(@Valid @RequestBody PhoneLoginVM phoneLoginVM) {

    }
}
