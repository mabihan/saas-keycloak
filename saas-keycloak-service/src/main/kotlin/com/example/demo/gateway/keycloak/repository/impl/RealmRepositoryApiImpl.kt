package com.example.demo.gateway.keycloak.repository.impl

import com.example.demo.gateway.keycloak.model.KeycloakRealmCreate
import com.example.demo.gateway.keycloak.repository.RealmRepository
import com.example.demo.model.TenantCreateDomain
import org.springframework.context.annotation.Profile
import javax.inject.Named

@Profile("keycloak-api")
@Named
class RealmRepositoryApiImpl: RealmRepository {

    override fun save(tenantCreateDomain: TenantCreateDomain): KeycloakRealmCreate? {
        TODO("Not yet implemented")
    }

    override fun delete(realm: String) {
        TODO("Not yet implemented")
    }
}
