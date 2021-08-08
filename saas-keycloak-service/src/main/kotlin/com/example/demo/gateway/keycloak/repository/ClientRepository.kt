package com.example.demo.gateway.keycloak.repository

import com.example.demo.gateway.keycloak.model.KeycloakRealmClientCreate
import org.springframework.stereotype.Repository

@Repository
interface ClientRepository {

    fun save(realm: String, client: KeycloakRealmClientCreate)

    fun findAll(realm: String)

    fun get(realm: String, clientId: String)
}
