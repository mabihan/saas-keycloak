package com.example.demo.gateway.keycloak.repository

import com.example.demo.gateway.keycloak.model.KeycloakRealm
import org.springframework.stereotype.Repository

@Repository
interface RealmRepository {

    fun save(realm: kotlin.String): KeycloakRealm?

    fun delete(realm: kotlin.String)
}
