package com.example.demo.gateway.keycloak.impl.realm

import com.example.demo.exception.InternalErrorException
import com.example.demo.gateway.keycloak.realm.CreateRealmGateway
import com.example.demo.gateway.keycloak.repository.RealmRepository
import com.example.demo.model.TenantCreateDomain
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Named

@Named
class CreateRealmGatewayImpl(
    private val realmRepository: RealmRepository): CreateRealmGateway {

    private val log: Logger = LoggerFactory.getLogger(CreateRealmGatewayImpl::class.java)

    override fun execute(tenantCreateDomain: TenantCreateDomain) {
        try {
            this.realmRepository.save(tenantCreateDomain)
        } catch (ex: Exception) {
            log.error("Internal error while creating realm ${tenantCreateDomain.namespace}", ex)
            throw InternalErrorException("Internal error while creating realm ${tenantCreateDomain.namespace}:" + ex.message)
        }
    }
}
