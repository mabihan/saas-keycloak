package com.example.demo.generator

import com.example.demo.config.properties.ApplicationProperties
import com.example.demo.gateway.keycloak.model.KeycloakRealmClientCreate

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
