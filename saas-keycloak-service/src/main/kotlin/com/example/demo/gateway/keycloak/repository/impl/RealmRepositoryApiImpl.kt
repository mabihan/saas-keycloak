package com.example.demo.gateway.keycloak.repository.impl

import com.example.demo.gateway.keycloak.model.KeycloakRealmCreate
import com.example.demo.gateway.keycloak.repository.RealmRepository
import org.keycloak.admin.client.Keycloak
import org.keycloak.representations.idm.RealmRepresentation
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Profile
import javax.inject.Named

@Profile("keycloak-api")
@Named
class RealmRepositoryApiImpl(
    private val keycloak: Keycloak
): RealmRepository {

    private val log: Logger = LoggerFactory.getLogger(RealmRepository::class.java)

    override fun save(realm: kotlin.String): KeycloakRealmCreate? {

        val realms = keycloak.realms().findAll().map { it.id }
        log.info("realms: " + realms.joinToString())
        if (realm !in realms) {
            log.info("will create realm: $realm")
            val realmRepresentation = RealmRepresentation()
            realmRepresentation.id = realm
            realmRepresentation.realm = realm
            realmRepresentation.isEnabled = true
            keycloak.realms().create(realmRepresentation)
        }

        val clientScope = keycloak.realm(realm)
            .clientScopes()
            .findAll()
            .first { it.name == "roles" }
        val protocolMapper = clientScope.protocolMappers
            .filter { it.name == "realm roles" }
            .onEach { it.config["claim.name"] = "roles" }
            .first()
        log.info ("update protocolMapper: ${protocolMapper.id} of clientScope: ${clientScope.id}")

        keycloak.realm(realm)
            .clientScopes()
            .get(clientScope.id)
            .protocolMappers
            .update(protocolMapper.id, protocolMapper)

        return null
    }

    override fun delete(realm: kotlin.String) {
        TODO("Not yet implemented")
    }
}
