package io.github.ewinds.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@Configuration
@EnableOAuth2Client
public class OAuth2ClientConfiguration {
    @Value("${application.oauth2.clientId}")
    private String oAuth2ClientId;

    @Value("${application.oauth2.clientSecret}")
    private String oAuth2ClientSecret;

    @Value("${application.oauth2.accesTokenUri}")
    private String accessTokenUri;

    @Bean
    public OAuth2RestTemplate oAuthRestTemplate() {
        ClientCredentialsResourceDetails resourceDetails = new ClientCredentialsResourceDetails();
        resourceDetails.setId("1");
        resourceDetails.setClientId(oAuth2ClientId);
        resourceDetails.setClientSecret(oAuth2ClientSecret);
        resourceDetails.setAccessTokenUri(accessTokenUri);

        OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceDetails, new DefaultOAuth2ClientContext());

        return restTemplate;
    }
}
