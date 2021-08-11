package com.example.demo.generator

import com.example.demo.gateway.keycloak.model.KeycloakRealmClientAccessCreate

class KeycloakRealmClientAccessCreateGenerator {

    fun generate(): KeycloakRealmClientAccessCreate {

        return KeycloakRealmClientAccessCreate(
            view = true,
            configure = true,
            manage = true
        )

    }
}
