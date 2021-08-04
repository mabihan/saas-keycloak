package com.example.demo.config.keycloak

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder
import org.keycloak.admin.client.Keycloak
import org.keycloak.admin.client.KeycloakBuilder
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(KeycloakAdministrationProperties::class)
class KeycloakApiConfig {

    @Bean
    fun keycloak(administrationProperties: KeycloakAdministrationProperties): Keycloak {
        return KeycloakBuilder
            .builder()
            .serverUrl(administrationProperties.authServerUrl)
            .realm(administrationProperties.realm)
            .username(administrationProperties.username)
            .password(administrationProperties.password)
            .clientId(administrationProperties.clientId)
            .resteasyClient(ResteasyClientBuilder().connectionPoolSize(10).build())
            .build()
    }
}
