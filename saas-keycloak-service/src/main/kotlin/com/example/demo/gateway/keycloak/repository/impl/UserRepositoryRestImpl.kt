package com.example.demo.gateway.keycloak.repository.impl

import com.example.demo.config.keycloak.KeycloakRestConfig
import com.example.demo.exception.keycloak.KeycloakRestCommunicationException
import com.example.demo.exception.user.UserAlreadyExistException
import com.example.demo.gateway.keycloak.model.KeycloakUser
import com.example.demo.gateway.keycloak.model.KeycloakUserCreate
import com.example.demo.gateway.keycloak.repository.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Profile
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.reactive.function.client.WebClient
import javax.inject.Named
import org.springframework.util.LinkedMultiValueMap





@Profile("keycloak-rest")
@Named
class UserRepositoryRestImpl(private val keycloakAuthenticatedWebClient: WebClient,
                             private val keycloakRestConfig: KeycloakRestConfig) : UserRepository {

    private val log: Logger = LoggerFactory.getLogger(UserRepositoryRestImpl::class.java)

    override fun save(realm: String, userToCreate: KeycloakUserCreate): String {

        var uuid = ""

        keycloakAuthenticatedWebClient
            .post()
            .uri("/admin/realms/${realm}/users")
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, "bearer ${this.keycloakRestConfig.getValidAuthorization().access_token}")
            .body(BodyInserters.fromValue(userToCreate))
            .exchangeToMono { response: ClientResponse ->
                if (response.statusCode() == HttpStatus.CREATED) {
                    log.info("Successfully created user ${userToCreate.username} in realm $realm in keycloak")
                    uuid = response.headers().header("Location")[0].split("/").last()
                    return@exchangeToMono response.bodyToMono(String::class.java)
                } else if (response.statusCode() ==  HttpStatus.CONFLICT) {
                    throw UserAlreadyExistException("User ${userToCreate.email} ${userToCreate.username} already exist.")
                } else {
                    throw KeycloakRestCommunicationException("Error while creating realm $realm : Got HTTP ${response.statusCode()} status code." )
                } }
            .block()

        return uuid
    }

    override fun sendVerificationEmail(realm: String, id: String) {

        val body: LinkedMultiValueMap<String, String> = LinkedMultiValueMap<String, String>()
        body.add("redirect_uri", "http://localhost:4300")

        keycloakAuthenticatedWebClient
            .put()
            .uri("/admin/realms/${realm}/users/${id}/send-verify-email")
            .contentType(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, "bearer ${this.keycloakRestConfig.getValidAuthorization().access_token}")
            //.body(BodyInserters.fromFormData(body))
            .exchangeToMono { response: ClientResponse ->
                if(response.statusCode() == HttpStatus.NO_CONTENT) {
                    log.debug("Sending verification email for user $id in realm $realm.")
                    return@exchangeToMono response.bodyToMono(String::class.java)
                } else {
                    throw KeycloakRestCommunicationException("Error while sending verification email for user $id in realm $realm : Got HTTP ${response.statusCode()} status code." )
                } }
            .block()

    }

    override fun search(realm: String, search: String): List<KeycloakUser> {
        TODO("Not yet implemented")
    }

    override fun findById(realm: String, id: String): KeycloakUser? {

        val keycloakUserListMono = keycloakAuthenticatedWebClient
            .get()
            .uri("/admin/realms/${realm}/users/${id}")
            .header(HttpHeaders.AUTHORIZATION, "bearer ${this.keycloakRestConfig.getValidAuthorization().access_token}")
            .retrieve()
            .bodyToMono(KeycloakUser::class.java)

        return keycloakUserListMono.block()
    }

    override fun findByUsername(realm: String, username: String, pageable: Pageable): Page<KeycloakUser> {

        val keycloakUserListMono = keycloakAuthenticatedWebClient
            .get()
            .uri("/admin/realms/${realm}/users?username=${username}&first=${pageable.pageNumber}&max=${pageable.pageSize}")
            .header(HttpHeaders.AUTHORIZATION, "bearer ${this.keycloakRestConfig.getValidAuthorization().access_token}")
            .retrieve()
            .bodyToMono(Array<KeycloakUser>::class.java)

        val keycloakUserList = keycloakUserListMono.block().orEmpty().asList()

        return PageImpl(
            keycloakUserList,
            pageable,
            keycloakUserList.size.toLong()
        )
    }

    override fun findByEmail(realm: String, email: String, pageable: Pageable): Page<KeycloakUser> {

        val keycloakUserListMono = keycloakAuthenticatedWebClient
            .get()
            .uri("/admin/realms/${realm}/users?email=${email}&first=${pageable.pageNumber}&max=${pageable.pageSize}")
            .header(HttpHeaders.AUTHORIZATION, "bearer ${this.keycloakRestConfig.getValidAuthorization().access_token}")
            .retrieve()
            .bodyToMono(Array<KeycloakUser>::class.java)

        val keycloakUserList = keycloakUserListMono.block().orEmpty().asList()

        return PageImpl(
            keycloakUserList,
            pageable,
            keycloakUserList.size.toLong()
        )
    }

    override fun findAll(realm: String, pageable: Pageable): Page<KeycloakUser> {

        val keycloakUserListMono = keycloakAuthenticatedWebClient
            .get()
            .uri("/admin/realms/${realm}/users?first=${pageable.pageNumber}&max=${pageable.pageSize}")
            .header(HttpHeaders.AUTHORIZATION, "bearer ${this.keycloakRestConfig.getValidAuthorization().access_token}")
            .retrieve()
            .bodyToMono(Array<KeycloakUser>::class.java)

        val keycloakUserList = keycloakUserListMono.block().orEmpty().asList()

        return PageImpl(
            keycloakUserList,
            pageable,
            keycloakUserList.size.toLong()
        )
    }

    private fun extractUuidFromResponse(clientResponse: ClientResponse): String {
        return clientResponse.headers().header("Location")[0].split("/").last()
    }
}
