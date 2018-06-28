package io.github.ewinds.repository;

import io.github.ewinds.domain.SmsCode;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;

import java.time.Instant;
import java.util.List;
import java.util.Optional;


/**
 * Spring Data JPA repository for the SmsCode entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SmsCodeRepository extends JpaRepository<SmsCode, Long> {
    Optional<SmsCode> findOneByPhoneAndCodeAndExpiredDateAfter(String phone, String code, Instant dateTime);

    List<SmsCode> findAllByExpiredDateBefore(Instant dateTime);

    Optional<SmsCode> findOneByPhoneAndExpiredDateAfter(String phone, Instant dateTime);
}
