package com.example.demo.gateway.database.impl

import com.example.demo.gateway.GetAllTenantsGateway
import com.example.demo.gateway.database.repository.TenantRepository
import com.example.demo.gateway.database.translator.TenantDBToTenantDomainTranslator
import com.example.demo.model.TenantDomain
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Pageable
import javax.inject.Named

@Named
class GetAllTenantsGatewayImpl(private val tenantRepository: TenantRepository): GetAllTenantsGateway {

    private val log: Logger = LoggerFactory.getLogger(GetAllTenantsGatewayImpl::class.java)

    override fun execute(pageable: Pageable): List<TenantDomain> {
        return this.tenantRepository.findAll(pageable)
            .map { tenantDB -> TenantDBToTenantDomainTranslator().translate(tenantDB) }
            .toList()
    }
}
