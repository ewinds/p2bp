package io.github.ewinds.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Properties specific to Loan.
 * <p>
 * Properties are configured in the application.yml file.
 * See {@link io.github.jhipster.config.JHipsterProperties} for a good example.
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

    private final List<KeyValue> serviceFees = new ArrayList<>();

    private final List<KeyValue> interestCalculations = new ArrayList<>();

    public static class KeyValue {
        public String key;
        public String value;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public List<KeyValue> getServiceFees() {
        return serviceFees;
    }

    public List<KeyValue> getInterestCalculations() {
        return interestCalculations;
    }
}
