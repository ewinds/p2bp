package io.github.ewinds.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Service class for managing users.
 */
@Service
@Transactional
public class ProductService {

    /**
     * Not activated users should be automatically deleted after 3 days.
     * <p>
     * This is scheduled to get fired every hour.
     */
    @Scheduled(cron = "0 0 * * * ?")
    public void failExpiredProduct() {
//        List<User> users = userRepository.findAllByActivatedIsFalseAndCreatedDateBefore(Instant.now().minus(3, ChronoUnit.DAYS));
//        for (User user : users) {
//            log.debug("Deleting not activated user {}", user.getLogin());
//            userRepository.delete(user);
//            cacheManager.getCache(UserRepository.USERS_BY_LOGIN_CACHE).evict(user.getLogin());
//            cacheManager.getCache(UserRepository.USERS_BY_EMAIL_CACHE).evict(user.getEmail());
//        }
    }
}
