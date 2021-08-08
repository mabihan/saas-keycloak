package com.example.demo.gateway.database.impl.tenant

import com.example.demo.exception.InternalErrorException
import com.example.demo.gateway.tenant.DeleteAllTenantsGateway
import com.example.demo.gateway.database.repository.TenantRepository
import com.example.demo.gateway.keycloak.repository.RealmRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import javax.inject.Named
import javax.persistence.EntityManager

@Named
@Transactional(readOnly = false, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
class DeleteAllTenantsGatewayImpl(private val tenantRepository: TenantRepository, private val realmRepository: RealmRepository, private val entityManager: EntityManager):
    DeleteAllTenantsGateway {

    private val log: Logger = LoggerFactory.getLogger(DeleteAllTenantsGatewayImpl::class.java)

    override fun execute() {

        log.warn("Trying to deleting all tenants in database")

        try {

            this.tenantRepository.findAll().forEach {
                val query = entityManager.createNativeQuery("DROP SCHEMA " + it.schema + ";")
                query.executeUpdate()
                this.tenantRepository.delete(it)
                log.warn("Deleted ${it.namespace} in database.")
                this.realmRepository.delete(it.namespace)
                log.warn("Deleted ${it.namespace} in keycloak.")
            }
        } catch (ex: Exception) {
            log.error("Internal error deleting tenant", ex)
            throw InternalErrorException("Internal error deleting tenant: " + ex.message)
        }

    }
}
