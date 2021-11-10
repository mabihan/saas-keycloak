package com.example.demo.gateway.keycloak.repository

import com.example.demo.gateway.keycloak.model.KeycloakClient
import com.example.demo.gateway.keycloak.model.KeycloakRealmClientCreate
import org.springframework.stereotype.Repository

@Repository
interface ClientRepository {

    fun save(realm: String, client: KeycloakRealmClientCreate)

    fun findAll(realm: String): List<KeycloakClient>

    fun get(realm: String, clientName: String)
}
