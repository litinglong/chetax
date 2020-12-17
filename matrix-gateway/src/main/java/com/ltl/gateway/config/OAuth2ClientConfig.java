package com.ltl.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@EnableOAuth2Client
@EnableConfigurationProperties
@Configuration
public class OAuth2ClientConfig {

    @Autowired
    OAuth2ProtectedResourceDetails auth2ProtectedResourceDetails;
    @Bean
    public OAuth2RestOperations restTemplate(OAuth2ClientContext oauth2ClientContext) {
        OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(auth2ProtectedResourceDetails, oauth2ClientContext);
        return restTemplate;
    }

//    @Bean
//    public RequestInterceptor oauth2FeignRequestInterceptor() {
//        return new OAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext(), clientCredentialsResourceDetails());
//    }

//    @Bean
//    public OAuth2RestTemplate clientCredentialsRestTemplate() {
//        return new OAuth2RestTemplate(clientCredentialsResourceDetails());
//    }
}
