package com.example.demo.gateway.keycloak.repository

import com.example.demo.gateway.keycloak.model.KeycloakRealmCreate
import org.springframework.stereotype.Repository

@Repository
interface RealmRepository {

    fun save(realm: kotlin.String): KeycloakRealmCreate?

    fun delete(realm: kotlin.String)
}
