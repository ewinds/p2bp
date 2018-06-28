package io.github.ewinds.web.rest.errors;

public class PhoneAlreadyUsedException extends BadRequestAlertException {

    public PhoneAlreadyUsedException() {
        super(ErrorConstants.PHONE_ALREADY_USED_TYPE, "Phone already in use", "userManagement", "phonexists");
    }
}
