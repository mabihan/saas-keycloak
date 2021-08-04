package com.example.demo.gateway.keycloak.repository

import com.example.demo.gateway.keycloak.model.KeycloakRealm
import org.keycloak.admin.client.resource.RealmResource
import org.keycloak.representations.idm.RealmRepresentation
import org.springframework.stereotype.Repository

@Repository
interface RealmRepository {

    fun save(realm: String): KeycloakRealm?

    fun delete(realm: String)
}
