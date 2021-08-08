package com.example.demo.gateway.keycloak.translator

import com.example.demo.gateway.keycloak.model.KeycloakUser
import com.example.demo.model.UserDomain

class KeycloakUserToUserDomainTranslator {

    fun translate(keycloakUser: KeycloakUser): UserDomain {

        return UserDomain(
            id = keycloakUser.id,
            firstName = keycloakUser.firstName,
            lastName = keycloakUser.lastName,
            email = keycloakUser.email,
            username = keycloakUser.email,
            createdTimestamp = keycloakUser.createdTimestamp,
            enabled = keycloakUser.enabled,
            totp = keycloakUser.totp,
            emailVerified = keycloakUser.emailVerified
        )
    }

}
