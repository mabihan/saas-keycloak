package com.example.demo.gateway.keycloak.repository.impl

import com.example.demo.config.keycloak.KeycloakRestConfig
import com.example.demo.config.properties.ApplicationProperties
import com.example.demo.exception.keycloak.KeycloakRestCommunicationException
import com.example.demo.gateway.keycloak.model.KeycloakRealm
import com.example.demo.gateway.keycloak.model.KeycloakSmtpServer
import com.example.demo.gateway.keycloak.repository.RealmRepository
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
class RealmRepositoryRestImpl(private val keycloakAuthenticatedWebClient: WebClient,
                              private val keycloakRestConfig: KeycloakRestConfig,
                              private val applicationProperties: ApplicationProperties) : RealmRepository {

    private val log: Logger = LoggerFactory.getLogger(RealmRepositoryRestImpl::class.java)

    override fun save(realm: String): KeycloakRealm? {

        val keycloakRealm = KeycloakRealm(id = realm, realm = realm, KeycloakSmtpServer(
            password = applicationProperties.keycloak.smtp.password,
            replyToDisplayName = applicationProperties.keycloak.smtp.replyToDisplayName,
            starttls = applicationProperties.keycloak.smtp.starttls,
            auth = applicationProperties.keycloak.smtp.auth,
            port = applicationProperties.keycloak.smtp.port,
            host = applicationProperties.keycloak.smtp.host,
            replyTo = applicationProperties.keycloak.smtp.replyTo,
            fromDisplayName = applicationProperties.keycloak.smtp.fromDisplayName,
            from = applicationProperties.keycloak.smtp.from,
            ssl = applicationProperties.keycloak.smtp.ssl,
            user = applicationProperties.keycloak.smtp.user
        ))

        return keycloakAuthenticatedWebClient
            .post()
            .uri("/admin/realms")
            .header(HttpHeaders.AUTHORIZATION, "bearer ${this.keycloakRestConfig.getValidAuthorization().access_token}")
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(keycloakRealm))
            .exchangeToMono { response: ClientResponse ->
                if (response.statusCode() == HttpStatus.CREATED) {
                    log.info("Successfully created realm $realm in keycloak")
                    return@exchangeToMono response.bodyToMono(KeycloakRealm::class.java)
                } else {
                    throw KeycloakRestCommunicationException("Error while creating realm $realm : Got HTTP ${response.statusCode()} status code from Keycloak." )
                } }
            .block()
    }

    override fun delete(realm: String) {

        log.warn("Trying to delete $realm in keycloak")

        keycloakAuthenticatedWebClient
            .delete()
            .uri("/admin/realms/$realm")
            .header(HttpHeaders.AUTHORIZATION, "bearer ${this.keycloakRestConfig.getValidAuthorization().access_token}")
            .retrieve()
            .onStatus(HttpStatus::isError) {
                throw KeycloakRestCommunicationException("Error while deleting realm $realm: $it")
            }
            .bodyToMono(Void::class.java)
            .block()
    }
}
