package io.github.ewinds.web.rest.vm;

import io.github.ewinds.config.Constants;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class PhoneLoginVM {
    public static final int SMS_CODE_LENGTH = 6;

    public static final int PHONE_LENGTH = 11;

    @NotNull
    @Pattern(regexp = Constants.PHONE_REGEX)
    @Size(min = PHONE_LENGTH, max = PHONE_LENGTH)
    private String phone;

    @NotNull
    @Size(min = SMS_CODE_LENGTH, max = SMS_CODE_LENGTH)
    private String smsCode;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }
}
