package com.example.demo.gateway.keycloak.repository.impl

import com.example.demo.config.keycloak.KeycloakAdministrationProperties
import com.example.demo.gateway.keycloak.model.KeycloakUser
import com.example.demo.gateway.keycloak.repository.UserRepository
import org.keycloak.admin.client.Keycloak
import org.keycloak.representations.idm.CredentialRepresentation
import org.keycloak.representations.idm.UserRepresentation
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Named
import kotlin.random.Random

@Named
class UserRepositoryImpl(
    private val properties: KeycloakAdministrationProperties,
    private val keycloak: Keycloak): UserRepository {

    private val log: Logger = LoggerFactory.getLogger(UserRepositoryImpl::class.java)

    override fun save(user: KeycloakUser): KeycloakUser {

        val userRepresentation = keycloak
            .realm(properties.realm)
            .users()
            .get(user.email)

        return if (userRepresentation === null) {
            this.createUser(user)
        } else {
            log.warn("User ${user.email} already exist in realm ${properties.realm}")
            user
        }
    }

    private fun createUser(user: KeycloakUser): KeycloakUser {
        val password = user.password ?: generatePassword()

        val credentialRepresentation = CredentialRepresentation()
        credentialRepresentation.type = CredentialRepresentation.PASSWORD
        credentialRepresentation.value = password

        val userRepresentation = UserRepresentation()
        userRepresentation.username = user.email
        userRepresentation.firstName = user.firstName
        userRepresentation.lastName = user.lastName
        userRepresentation.isEnabled = true
        userRepresentation.credentials  = listOf(credentialRepresentation)

        keycloak
            .realm(properties.realm)
            .users()
            .create(userRepresentation)

        return KeycloakUser(user.firstName, user.lastName, user.email, password)
    }

    private fun generatePassword(): String {
        val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        val length = 32

        return (1..length)
            .map { i -> Random.nextInt(0, charPool.size) }
            .map(charPool::get)
            .joinToString("")
    }
}
