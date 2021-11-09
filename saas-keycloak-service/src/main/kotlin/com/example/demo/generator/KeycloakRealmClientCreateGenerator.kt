package com.example.demo.generator

import com.example.demo.config.properties.ApplicationProperties
import com.example.demo.gateway.keycloak.model.KeycloakRealmClientCreate
import java.net.URI
import java.time.LocalDateTime

class KeycloakRealmClientCreateGenerator {

    fun generate(applicationProperties: ApplicationProperties): KeycloakRealmClientCreate {

        return KeycloakRealmClientCreate(
            name = applicationProperties.keycloak.client.clientId,
            redirectUris = applicationProperties.keycloak.client.redirectUris.map { "$it/*" },
            webOrigins = applicationProperties.keycloak.client.redirectUris.map { it.toString() },
            publicClient = true
        )

    }
}
