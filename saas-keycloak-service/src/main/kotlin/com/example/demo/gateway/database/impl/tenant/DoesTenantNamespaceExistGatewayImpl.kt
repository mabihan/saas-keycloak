package com.example.demo.gateway.database.impl.tenant

import com.example.demo.exception.InternalErrorException
import com.example.demo.gateway.tenant.DoesTenantUuidExistGateway
import com.example.demo.gateway.database.repository.TenantRepository
import com.example.demo.gateway.tenant.DoesTenantNamespaceExistGateway
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.lang.Exception
import java.util.*
import javax.inject.Named

@Named
class DoesTenantNamespaceExistGatewayImpl(private val tenantRepository: TenantRepository) : DoesTenantNamespaceExistGateway {

    private val log: Logger = LoggerFactory.getLogger(DoesTenantNamespaceExistGatewayImpl::class.java)

    override fun execute(namespace: String): Boolean {
        try {
            return tenantRepository.existsByNamespace(namespace)
        } catch (ex: Exception) {
            log.error("Internal error to find the tenant", ex)
            throw InternalErrorException("Internal error to find the tenant: " + ex.message)
        }
    }

}
