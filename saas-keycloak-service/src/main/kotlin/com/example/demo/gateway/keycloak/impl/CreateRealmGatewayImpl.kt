package com.example.demo.gateway.keycloak.impl

import com.example.demo.exception.InternalErrorException
import com.example.demo.gateway.CreateRealmGateway
import com.example.demo.gateway.keycloak.repository.RealmRepository
import com.example.demo.model.TenantDomain
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Named

@Named
class CreateRealmGatewayImpl(private val realmRepository: RealmRepository): CreateRealmGateway {

    private val log: Logger = LoggerFactory.getLogger(CreateRealmGatewayImpl::class.java)

    override fun execute(tenantDomain: TenantDomain) {
        try {
            this.realmRepository.save(tenantDomain.namespace)
        } catch (ex: Exception) {
            log.error("Internal error while creating realm ${tenantDomain.namespace}", ex)
            throw InternalErrorException("Internal error while creating realm ${tenantDomain.namespace}:" + ex.message)
        }
    }
}
