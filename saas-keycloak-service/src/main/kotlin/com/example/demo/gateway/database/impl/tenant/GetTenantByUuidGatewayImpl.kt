package com.example.demo.gateway.database.impl.tenant

import com.example.demo.exception.tenant.TenantNotFoundException
import com.example.demo.exception.tenant.TenantUuidMalformedException
import com.example.demo.gateway.database.repository.TenantRepository
import com.example.demo.gateway.database.translator.TenantDBToTenantDomainTranslator
import com.example.demo.gateway.tenant.GetTenantByUuidGateway
import com.example.demo.model.TenantDomain
import java.util.*
import javax.inject.Named

@Named
class GetTenantByUuidGatewayImpl(private val tenantRepository: TenantRepository): GetTenantByUuidGateway {

    override fun execute(uuid: String): TenantDomain {

        try {
            val validUuid = UUID.fromString(uuid)

            if (!this.tenantRepository.existsByUuid(validUuid)) {
                throw TenantNotFoundException("Tenant with uuid $uuid does not exist." )
            }

            return TenantDBToTenantDomainTranslator().translate(this.tenantRepository.findByUuid(validUuid))
        } catch (ex: IllegalArgumentException ) {
            throw TenantUuidMalformedException("$uuid is not a valid UUID")
        }
   }
}
