package com.example.demo.gateway.keycloak.repository

import com.example.demo.gateway.keycloak.model.KeycloakRealmCreate
import com.example.demo.model.TenantCreateDomain
import org.springframework.stereotype.Repository

@Repository
interface RealmRepository {

    fun save(tenantCreateDomain: TenantCreateDomain): KeycloakRealmCreate?

    fun delete(realm: String)
}
