package com.example.demo.gateway.keycloak.impl

import com.example.demo.exception.InternalErrorException
import com.example.demo.gateway.CreateRealmGateway
import com.example.demo.gateway.DeleteRealmGateway
import com.example.demo.gateway.database.translator.TenantDomainToRealmRepresentationTranslator
import com.example.demo.gateway.keycloak.repository.RealmRepository
import com.example.demo.model.TenantDomain
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Named

@Named
class DeleteRealmGatewayImpl(private val realmRepository: RealmRepository): DeleteRealmGateway {

    private val log: Logger = LoggerFactory.getLogger(DeleteRealmGatewayImpl::class.java)

    override fun execute(realm: String) {
        try {
            this.realmRepository.delete(realm)
        } catch (ex: Exception) {
            log.error("Internal error while deleting realm $realm", ex)
            throw InternalErrorException("Internal error while deleting realm $realm :" + ex.message)
        }
    }
}
