package io.github.ewinds.service;

import io.github.ewinds.config.ApplicationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring4.SpringTemplateEngine;

import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Stream;

/**
 * Service for sending sms.
 * <p>
 * We use the @Async annotation to send sms asynchronously.
 */
@Service
public class SmsService {
    private final Logger log = LoggerFactory.getLogger(SmsService.class);

    private final ApplicationProperties applicationProperties;

    private final MessageSource messageSource;

    private final SpringTemplateEngine templateEngine;

    public SmsService(ApplicationProperties applicationProperties, MessageSource messageSource, SpringTemplateEngine templateEngine) {
        this.applicationProperties = applicationProperties;
        this.messageSource = messageSource;
        this.templateEngine = templateEngine;
    }

    @Async
    public void sendSms(String to, String content) {
        log.debug("Send sms to '{}' with content={}", to, content);

        // Prepare message using a Spring helper
        try {
            log.info("Send sms to '{}' with content={}", to, content);
            log.debug("Sent sms to User '{}'", to);
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.warn("Sms could not be sent to user '{}'", to, e);
            } else {
                log.warn("Sms could not be sent to user '{}': {}", to, e.getMessage());
            }
        }
    }

    @Async
    public void sendSmsFromTemplate(String to, String templateName, String titleKey, Object... params) {
        Locale locale = Locale.getDefault();
//        Context context = new Context(locale);
//        context.setVariable(USER, user);
//        context.setVariable(BASE_URL, jHipsterProperties.getMail().getBaseUrl());
//        String content = templateEngine.process(templateName, context);
        String content = messageSource.getMessage(titleKey, Stream.concat(Arrays.stream(new String[]{applicationProperties.getName()}), Arrays.stream(params)).toArray(), locale);
        sendSms(to, content);
    }

    @Async
    public void sendVerifySms(String to, Object... params) {
        log.debug("Sending verify sms to '{}'", to);
        sendSmsFromTemplate(to, "verifySms", "sms.verify.content", params);
    }
}
