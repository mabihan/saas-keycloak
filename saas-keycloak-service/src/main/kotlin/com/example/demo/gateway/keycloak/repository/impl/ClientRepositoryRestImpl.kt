package com.example.demo.gateway.keycloak.repository.impl

import com.example.demo.config.keycloak.KeycloakRestConfig
import com.example.demo.config.properties.ApplicationProperties
import com.example.demo.exception.keycloak.KeycloakRestCommunicationException
import com.example.demo.gateway.keycloak.model.KeycloakRealmClientCreate
import com.example.demo.gateway.keycloak.model.KeycloakRealmCreate
import com.example.demo.gateway.keycloak.repository.ClientRepository
import com.example.demo.generator.KeycloakRealmClientCreateGenerator
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Profile
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.reactive.function.client.WebClient
import javax.inject.Named

@Profile("keycloak-rest")
@Named
class ClientRepositoryRestImpl(private val keycloakAuthenticatedWebClient: WebClient,
                               private val keycloakRestConfig: KeycloakRestConfig) : ClientRepository {

    private val log: Logger = LoggerFactory.getLogger(ClientRepositoryRestImpl::class.java)

    override fun save(realm: String, client: KeycloakRealmClientCreate) {

        keycloakAuthenticatedWebClient
            .post()
            .uri("/admin/realms/$realm/clients")
            .header(HttpHeaders.AUTHORIZATION, "bearer ${this.keycloakRestConfig.getValidAuthorization().access_token}")
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(client))
            .exchangeToMono { response: ClientResponse ->
                if (response.statusCode() == HttpStatus.CREATED) {
                    log.info("Successfully created client on realm $realm in keycloak")
                    return@exchangeToMono response.bodyToMono(KeycloakRealmClientCreate::class.java)
                } else if (response.statusCode() == HttpStatus.CONFLICT){
                    log.warn("Client already created on realm $realm in keycloak")
                    return@exchangeToMono response.bodyToMono(KeycloakRealmClientCreate::class.java)
                } else {
                    throw KeycloakRestCommunicationException("Error while creating client on realm $realm : Got HTTP ${response.statusCode()} status code from Keycloak." )
                } }
            .block()
    }

    override fun findAll(realm: String) {
        TODO("Not yet implemented")
    }

    override fun get(realm: String, clientId: String) {
        TODO("Not yet implemented")
    }
}
