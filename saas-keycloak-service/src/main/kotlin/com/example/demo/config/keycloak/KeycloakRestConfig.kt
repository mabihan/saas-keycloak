package com.example.demo.config.keycloak

import com.example.demo.exception.keycloak.KeycloakRestCommunicationException
import com.example.demo.gateway.keycloak.model.KeycloakAuthorization
import lombok.Getter
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
import java.util.function.Consumer


@Getter
@Configuration
@EnableConfigurationProperties(KeycloakAdministrationProperties::class)
class KeycloakRestConfig {

    private val log: Logger = LoggerFactory.getLogger(KeycloakRestConfig::class.java)

    @Bean
    fun keycloakWebClient(administrationProperties: KeycloakAdministrationProperties): WebClient {

        return WebClient.builder()
            .baseUrl(administrationProperties.authServerUrl)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
            .defaultHeader(HttpHeaders.AUTHORIZATION,
                "bearer ${this.authorize(administrationProperties).block()?.access_token}")
            .filter(logRequest())
            .filter(logResponse())
            .build()

    }

    private fun authorize(administrationProperties: KeycloakAdministrationProperties): Mono<KeycloakAuthorization> {

        val formData: MultiValueMap<String, String> = LinkedMultiValueMap()
        formData.add("grant_type", "password")
        formData.add("client_id", administrationProperties.clientId)
        formData.add("username", administrationProperties.username)
        formData.add("password", administrationProperties.password)


        return WebClient.create(administrationProperties.authServerUrl)
            .post()
            .uri("/realms/${administrationProperties.realm}/protocol/openid-connect/token")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .body(BodyInserters.fromFormData(formData))
            .exchangeToMono { response: ClientResponse ->
                if (response.statusCode() == HttpStatus.OK) {
                    log.info("Successfully authorized client against ${administrationProperties.authServerUrl}.")
                    return@exchangeToMono response.bodyToMono(KeycloakAuthorization::class.java)
                } else {
                    throw KeycloakRestCommunicationException("Error while authorizing client : Got HTTP ${response.statusCode()} status code." )
                } }
    }

    // This method returns filter function which will log request data
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

    // This method returns filter function which will log response data
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
}
