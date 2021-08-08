package com.example.demo.config.keycloak

import com.example.demo.config.properties.ApplicationProperties
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder
import org.keycloak.admin.client.Keycloak
import org.keycloak.admin.client.KeycloakBuilder
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(ApplicationProperties::class)
class KeycloakApiConfig {

    @Bean
    fun keycloak(properties: ApplicationProperties): Keycloak {
        return KeycloakBuilder
            .builder()
            .serverUrl(properties.keycloak.authServerUrl)
            .realm(properties.keycloak.realm)
            .username(properties.keycloak.username)
            .password(properties.keycloak.password)
            .clientId(properties.keycloak.clientId)
            .resteasyClient(ResteasyClientBuilder().connectionPoolSize(10).build())
            .build()
    }
}
