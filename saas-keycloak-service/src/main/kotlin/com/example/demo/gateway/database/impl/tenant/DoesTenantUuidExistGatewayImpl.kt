package com.example.demo.gateway.database.impl.tenant

import com.example.demo.exception.InternalErrorException
import com.example.demo.gateway.tenant.DoesTenantUuidExistGateway
import com.example.demo.gateway.database.repository.TenantRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.lang.Exception
import java.util.*
import javax.inject.Named

@Named
class DoesTenantUuidExistGatewayImpl(private val tenantRepository: TenantRepository) : DoesTenantUuidExistGateway {

    private val log: Logger = LoggerFactory.getLogger(DoesTenantUuidExistGatewayImpl::class.java)

    override fun execute(uuid: UUID): Boolean {
        try {
            return tenantRepository.existsByUuid(uuid)
        } catch (ex: Exception) {
            log.error("Internal error to find the tenant", ex)
            throw InternalErrorException("Internal error to find the tenant: " + ex.message)
        }
    }

}
