package com.example.demo.gateway.keycloak.impl.client

import com.example.demo.config.properties.ApplicationProperties
import com.example.demo.exception.InternalErrorException
import com.example.demo.gateway.keycloak.client.CreateClientGateway
import com.example.demo.gateway.keycloak.realm.CreateRealmGateway
import com.example.demo.gateway.keycloak.repository.ClientRepository
import com.example.demo.gateway.keycloak.repository.RealmRepository
import com.example.demo.gateway.keycloak.translator.UserDomainToKeycloakUserCreateTranslator
import com.example.demo.generator.KeycloakRealmClientCreateGenerator
import com.example.demo.model.TenantDomain
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Named

@Named
class CreateClientGatewayImpl(private val clientRepository: ClientRepository,
                              private val applicationProperties: ApplicationProperties): CreateClientGateway {

    private val log: Logger = LoggerFactory.getLogger(CreateClientGatewayImpl::class.java)

    override fun execute(realm: String) {
        this.clientRepository.save(realm, KeycloakRealmClientCreateGenerator().generate(realm, this.applicationProperties))
    }
}
