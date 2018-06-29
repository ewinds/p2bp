package io.github.ewinds.service;

import io.github.ewinds.domain.SmsCode;
import io.github.ewinds.repository.SmsCodeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * Service Implementation for managing SmsCode.
 */
@Service
@Transactional
public class SmsCodeService {

    private final Logger log = LoggerFactory.getLogger(SmsCodeService.class);

    private final SmsCodeRepository smsCodeRepository;

    private final SmsService smsService;

    public SmsCodeService(SmsCodeRepository smsCodeRepository, SmsService smsService) {
        this.smsCodeRepository = smsCodeRepository;
        this.smsService = smsService;
    }

    /**
     * Save a smsCode.
     *
     * @param smsCode the entity to save
     * @return the persisted entity
     */
    public SmsCode save(SmsCode smsCode) {
        log.debug("Request to fetch SmsCode : {}", smsCode);
        String verifyCode = String.valueOf(new Random().nextInt(899999) + 100000);
        SmsCode newSmsCode = new SmsCode().phone(smsCode.getPhone()).code(verifyCode).expiredDate(Instant.now().plus(5, ChronoUnit.MINUTES));
        smsCodeRepository.save(newSmsCode);
        smsService.sendVerifySms(newSmsCode.getPhone(), newSmsCode.getCode());
        return newSmsCode;
    }

    /**
     * Get one smsCode by phone and code.
     *
     * @param phone the phone of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<SmsCode> findValidOne(String phone, String code) {
        log.debug("Request to get SmsCode : {}", phone);
        return smsCodeRepository.findOneByPhoneAndCodeAndExpiredDateAfter(phone, code, Instant.now().minus(5, ChronoUnit.MINUTES));
    }

    /**
     * Delete the expired smsCodes every 5 minutes.
     */
    @Scheduled(cron = "0 0/5 * * * ?")
    public void delete() {
        List<SmsCode> smsCodes = smsCodeRepository.findAllByExpiredDateBefore(Instant.now());
        for (SmsCode smsCode : smsCodes) {
            log.debug("Deleting expired smsCode {}", smsCode.getPhone());
            smsCodeRepository.delete(smsCode);
        }
    }
}
