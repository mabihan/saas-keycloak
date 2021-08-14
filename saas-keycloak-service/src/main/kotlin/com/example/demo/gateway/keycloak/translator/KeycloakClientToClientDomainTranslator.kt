package com.example.demo.gateway.keycloak.translator

import com.example.demo.gateway.keycloak.model.KeycloakClient
import com.example.demo.model.ClientDomain

class KeycloakClientToClientDomainTranslator {

    fun translate(keycloakClient: KeycloakClient): ClientDomain {

        return ClientDomain(
            id = keycloakClient.id,
            name = keycloakClient.name,
            clientId = keycloakClient.clientId,
            rootUrl = keycloakClient.rootUrl.orEmpty(),
            baseUrl = keycloakClient.baseUrl.orEmpty(),
        )
    }

}
