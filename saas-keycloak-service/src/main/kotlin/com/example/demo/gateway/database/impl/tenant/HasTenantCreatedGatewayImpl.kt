package com.example.demo.gateway.database.impl.tenant

import com.example.demo.exception.InternalErrorException
import com.example.demo.gateway.tenant.HasTenantCreatedGateway
import com.example.demo.gateway.database.repository.TenantRepository
import com.example.demo.model.TenantDomain
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.lang.Exception
import javax.inject.Named

@Named
class HasTenantCreatedGatewayImpl(private val tenantRepository: TenantRepository) : HasTenantCreatedGateway {

    private val log: Logger = LoggerFactory.getLogger(HasTenantCreatedGatewayImpl::class.java)

    override fun execute(tenantNamespace: String): Boolean {
        try {
            return tenantRepository.existsByNamespace(tenantNamespace)
        } catch (ex: Exception) {
            log.error("Internal error to find the tenant", ex)
            throw InternalErrorException("Internal error to find the tenant: " + ex.message)
        }
    }

}
