package com.example.demo.config.keycloak

import com.example.demo.config.properties.ApplicationProperties
import com.example.demo.exception.keycloak.KeycloakRestCommunicationException
import com.example.demo.gateway.keycloak.model.KeycloakAuthorization
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.ClientRequest
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.reactive.function.client.ExchangeFilterFunction
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import java.util.*
import java.util.function.Consumer

@Configuration
@EnableConfigurationProperties(ApplicationProperties::class)
class KeycloakRestConfig {

    private val log: Logger = LoggerFactory.getLogger(KeycloakRestConfig::class.java)

    lateinit var authorization: KeycloakAuthorization
    lateinit var properties: ApplicationProperties

    @Bean
    fun keycloakAuthenticatedWebClient(properties: ApplicationProperties): WebClient {

        this.properties = properties
        this.authorization = this.authorize(properties).block()!!

        return WebClient.builder()
            .baseUrl(properties.keycloak.authServerUrl)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
            .filter(logRequest())
            .filter(logResponse())
            .build()
    }

    fun getValidAuthorization(): KeycloakAuthorization {

        if (this.isAuthorizationExpired()) {

            if (this.isRefreshTokenExpired()) {
                log.warn("Client authorization refresh token expired")
                this.authorization = this.authorize(this.properties).block()!!
            } else {
                log.debug("Client authorization expired")

                val formData: MultiValueMap<String, String> = LinkedMultiValueMap()
                formData.add("grant_type", "refresh_token")
                formData.add("client_id", this.properties.keycloak.clientId)
                formData.add("refresh_token", this.authorization.refresh_token)

                this.authorization = WebClient.create(this.properties.keycloak.authServerUrl)
                    .post()
                    .uri("/realms/${this.properties.keycloak.realm}/protocol/openid-connect/token")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(BodyInserters.fromFormData(formData))
                    .exchangeToMono { response: ClientResponse ->
                        if (response.statusCode() == HttpStatus.OK) {
                            log.info("Successfully refresh authorization token against ${this.properties.keycloak.authServerUrl}.")
                            return@exchangeToMono response.bodyToMono(KeycloakAuthorization::class.java)
                        } else {
                            log.error(response.toString())
                            throw KeycloakRestCommunicationException("Error while authorizing client : Got HTTP ${response.statusCode()} status code." )
                        } }
                    .block()!!
            }
        }

        return this.authorization
    }

    private fun authorize(properties: ApplicationProperties): Mono<KeycloakAuthorization> {

        val formData: MultiValueMap<String, String> = LinkedMultiValueMap()
        formData.add("grant_type", "password")
        formData.add("client_id", this.properties.keycloak.clientId)
        formData.add("username", properties.keycloak.username)
        formData.add("password", properties.keycloak.password)


        return WebClient.create(properties.keycloak.authServerUrl)
            .post()
            .uri("/realms/${properties.keycloak.realm}/protocol/openid-connect/token")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .body(BodyInserters.fromFormData(formData))
            .exchangeToMono { response: ClientResponse ->
                if (response.statusCode() == HttpStatus.OK) {
                    log.info("Successfully authorized client against ${properties.keycloak.authServerUrl}.")
                    return@exchangeToMono response.bodyToMono(KeycloakAuthorization::class.java)
                } else {
                    throw KeycloakRestCommunicationException("Error while authorizing client : Got HTTP ${response.statusCode()} status code." )
                } }
    }

    /**
     * This method returns filter function which will log request data
     */
    private fun logRequest(): ExchangeFilterFunction {
        return ExchangeFilterFunction.ofRequestProcessor { clientRequest: ClientRequest ->
            log.debug("Request: {} {}", clientRequest.method(), clientRequest.url())
            clientRequest.headers()
                .forEach { name: String?, values: List<String?> ->
                    values.forEach(Consumer { value: String? -> log.debug("{}={}",name,value) })
                }
            Mono.just(clientRequest)
        }
    }

    /**
     * This method returns filter function which will log response data
     */
    private fun logResponse(): ExchangeFilterFunction {
        return ExchangeFilterFunction.ofResponseProcessor { clientResponse: ClientResponse ->
            log.debug("Response status: {}", clientResponse.statusCode())
            clientResponse.headers().asHttpHeaders()
                .forEach { name: String?, values: List<String?> ->
                    values.forEach(Consumer { value: String? -> log.debug("{}={}",name,value) })
                }
            Mono.just(clientResponse)
        }
    }

    /**
     * This method check whether the authorization token is expired.
     */
    private fun isAuthorizationExpired(): Boolean {
        log.debug("Check authorization token expiration.")
        return this.isTokenExpired(this.authorization.access_token)
    }


    private fun isRefreshTokenExpired(): Boolean {
        log.debug("Check refresh token expiration.")
        return this.isTokenExpired(this.authorization.refresh_token)
    }

    private fun isTokenExpired(signedToken: String): Boolean {

        val tokenOnly = signedToken.substring(0, signedToken.lastIndexOf('.') + 1)

        return try {
            val expiration = (Jwts.parser().parse(tokenOnly).body as Claims).expiration
            val remainingTime = (expiration.time -Date().time)/1000

            log.debug("Token will expire in $remainingTime seconds.")

            false

        } catch (ex: ExpiredJwtException) {
            log.debug(ex.localizedMessage)

            true
        }
    }
}
