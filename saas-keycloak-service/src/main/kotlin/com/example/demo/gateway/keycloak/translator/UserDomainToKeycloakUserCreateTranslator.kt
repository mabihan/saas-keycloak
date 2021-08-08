package com.example.demo.gateway.keycloak.translator

import com.example.demo.gateway.keycloak.model.KeycloakUserCreate
import com.example.demo.model.UserCreateDomain

class UserDomainToKeycloakUserCreateTranslator {

    fun translate(userCreateDomain: UserCreateDomain): KeycloakUserCreate {

        return KeycloakUserCreate(
            firstName = userCreateDomain.firstName,
            lastName = userCreateDomain.lastName,
            email = userCreateDomain.email,
            username = userCreateDomain.email,
            enabled = true,
            totp = false,
            emailVerified = false
        )
    }
}
