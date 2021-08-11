package com.example.demo.gateway.database.impl.tenant

import com.example.demo.gateway.database.repository.TenantRepository
import com.example.demo.gateway.database.translator.TenantDBToTenantDomainTranslator
import com.example.demo.gateway.tenant.GetAllTenantsGateway
import com.example.demo.model.TenantDomain
import org.springframework.data.domain.Pageable
import javax.inject.Named

@Named
class GetAllTenantsGatewayImpl(private val tenantRepository: TenantRepository): GetAllTenantsGateway {

    override fun execute(pageable: Pageable): List<TenantDomain> {
        return this.tenantRepository.findAll(pageable)
            .map { tenantDB -> TenantDBToTenantDomainTranslator().translate(tenantDB) }
            .toList()
    }
}
