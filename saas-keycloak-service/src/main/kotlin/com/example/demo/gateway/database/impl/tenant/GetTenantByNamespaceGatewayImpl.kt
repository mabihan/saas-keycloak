package com.example.demo.gateway.database.impl.tenant

import com.example.demo.exception.tenant.TenantNotFoudException
import com.example.demo.gateway.database.repository.TenantRepository
import com.example.demo.gateway.database.translator.TenantDBToTenantDomainTranslator
import com.example.demo.gateway.tenant.GetTenantByNamespaceGateway
import com.example.demo.model.TenantDomain
import javax.inject.Named

@Named
class GetTenantByNamespaceGatewayImpl(private val tenantRepository: TenantRepository): GetTenantByNamespaceGateway {

    override fun execute(namespace: String): TenantDomain {

        if (!this.tenantRepository.existsByNamespace(namespace)) {
            throw TenantNotFoudException("Tenant with namespace $namespace does not exist." )
        }

        return TenantDBToTenantDomainTranslator().translate(this.tenantRepository.findByNamespace(namespace))
    }
}
