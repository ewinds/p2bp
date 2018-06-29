package io.github.ewinds.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.ewinds.domain.SmsCode;
import io.github.ewinds.service.SmsCodeService;
import io.github.ewinds.web.rest.errors.InvalidSmsCodeException;
import io.github.ewinds.web.rest.vm.PhoneRegisterVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
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

    public AccountResource(SmsCodeService smsCodeService) {
        this.smsCodeService = smsCodeService;
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
    }
}
