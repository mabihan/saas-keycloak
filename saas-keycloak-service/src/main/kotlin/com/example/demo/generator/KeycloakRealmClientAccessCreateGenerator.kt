package com.example.demo.generator

import com.example.demo.config.properties.ApplicationProperties
import com.example.demo.gateway.keycloak.model.KeycloakRealmClientAccessCreate
import com.example.demo.gateway.keycloak.model.KeycloakRealmClientCreate
import java.net.URI

class KeycloakRealmClientAccessCreateGenerator {

    fun generate(applicationProperties: ApplicationProperties): KeycloakRealmClientAccessCreate {

        return KeycloakRealmClientAccessCreate(
            view = true,
            configure = true,
            manage = true
        )

    }
}
