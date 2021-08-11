package com.example.demo.generator

import com.example.demo.config.properties.ApplicationProperties
import com.example.demo.gateway.keycloak.model.KeycloakRealmClientCreate
import java.net.URI
import java.time.LocalDateTime

class KeycloakRealmClientCreateGenerator {

    fun generate(realm: String, applicationProperties: ApplicationProperties): KeycloakRealmClientCreate {

        return KeycloakRealmClientCreate(
            name = "${applicationProperties.keycloak.client.namePrefix}-${realm}",
            adminUrl = applicationProperties.keycloak.client.adminUrl.toString(),
            rootUrl = applicationProperties.keycloak.client.rootUrl.toString(),
            description = "Client created via REST API on ${LocalDateTime.now()}",
            surrogateAuthRequired = false,
            enabled = true,
            alwaysDisplayInConsole = false,
            clientAuthenticatorType = applicationProperties.keycloak.client.clientAuthenticatorType,
            redirectUris = applicationProperties.keycloak.client.redirectUris.map(URI::toString),
            webOrigins = listOf("*"),
            notBefore = 0,
            bearerOnly = false,
            consentRequired = false,
            standardFlowEnabled = true,
            implicitFlowEnabled = true,
            directAccessGrantsEnabled = true,
            serviceAccountsEnabled = false,
            publicClient = true,
            frontchannelLogout = false,
            protocol = applicationProperties.keycloak.client.protocol,
            fullScopeAllowed = true,
            nodeReRegistrationTimeout = -1,
            defaultClientScopes = listOf("web-origins","profile","roles","email"),
            optionalClientScopes = listOf("address","phone","offline_access","microprofile-jwt"),
            access = KeycloakRealmClientAccessCreateGenerator().generate()
        )

    }
}
