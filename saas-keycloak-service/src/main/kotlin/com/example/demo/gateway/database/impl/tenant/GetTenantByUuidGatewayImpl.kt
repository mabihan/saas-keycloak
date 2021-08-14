package com.example.demo.gateway.database.impl.tenant

import com.example.demo.exception.tenant.TenantNotFoundException
import com.example.demo.exception.tenant.TenantUuidMalformedException
import com.example.demo.gateway.database.repository.TenantRepository
import com.example.demo.gateway.database.translator.TenantDBToTenantDomainTranslator
import com.example.demo.gateway.keycloak.repository.ClientRepository
import com.example.demo.gateway.keycloak.translator.KeycloakClientToClientDomainTranslator
import com.example.demo.gateway.tenant.GetTenantByUuidGateway
import com.example.demo.model.TenantDomain
import java.util.*
import java.util.stream.Collectors
import javax.inject.Named

@Named
class GetTenantByUuidGatewayImpl(private val tenantRepository: TenantRepository,
                                 private val clientRepository: ClientRepository): GetTenantByUuidGateway {

    override fun execute(uuid: String): TenantDomain {

        try {
            val validUuid = UUID.fromString(uuid)

            if (!this.tenantRepository.existsByUuid(validUuid)) {
                throw TenantNotFoundException("Tenant with uuid $uuid does not exist." )
            }

            val tenantDb = this.tenantRepository.findByUuid(validUuid)

            val keycloakClientsDomains = this.clientRepository.findAll(tenantDb.keycloakRealm).stream()
                .map { keycloakClient -> KeycloakClientToClientDomainTranslator().translate(keycloakClient) }
                .collect(Collectors.toList())

            return TenantDBToTenantDomainTranslator().translate(tenantDb, keycloakClientsDomains)

        } catch (ex: IllegalArgumentException ) {
            throw TenantUuidMalformedException("$uuid is not a valid UUID")
        }
   }
}
