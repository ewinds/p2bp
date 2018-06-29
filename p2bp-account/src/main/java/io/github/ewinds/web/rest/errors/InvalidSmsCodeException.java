package io.github.ewinds.web.rest.errors;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class InvalidSmsCodeException extends AbstractThrowableProblem {

    public InvalidSmsCodeException() {
        super(ErrorConstants.INVALID_SMS_CODE_TYPE, "Incorrect sms code", Status.BAD_REQUEST);
    }
}
