package io.github.ewinds.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific to Account.
 * <p>
 * Properties are configured in the application.yml file.
 * See {@link io.github.jhipster.config.JHipsterProperties} for a good example.
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {
    private String name = "p2bp";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
