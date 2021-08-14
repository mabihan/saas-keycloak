package com.example.demo.gateway.keycloak.impl.client

import com.example.demo.config.properties.ApplicationProperties
import com.example.demo.exception.keycloak.KeycloakClientAlreadyExistException
import com.example.demo.gateway.keycloak.client.CreateClientGateway
import com.example.demo.gateway.keycloak.client.GetAllClientsGateway
import com.example.demo.gateway.keycloak.repository.ClientRepository
import com.example.demo.generator.KeycloakRealmClientCreateGenerator
import javax.inject.Named

@Named
class GetAllClientsGatewayImpl(private val clientRepository: ClientRepository,
                               private val applicationProperties: ApplicationProperties): GetAllClientsGateway {

    override fun execute(realm: String) {
        this.clientRepository.save(realm,
            KeycloakRealmClientCreateGenerator().generate(this.applicationProperties))
    }
}
