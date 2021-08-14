package com.example.demo.gateway.database.impl.tenant

import com.example.demo.exception.tenant.TenantNotFoundException
import com.example.demo.gateway.database.repository.TenantRepository
import com.example.demo.gateway.database.translator.TenantDBToTenantDomainTranslator
import com.example.demo.gateway.keycloak.repository.ClientRepository
import com.example.demo.gateway.keycloak.translator.KeycloakClientToClientDomainTranslator
import com.example.demo.gateway.tenant.GetTenantByNamespaceGateway
import com.example.demo.model.TenantDomain
import com.example.demo.translator.TenantDomainToTenantResponseTranslator
import java.util.stream.Collectors
import javax.inject.Named

@Named
class GetTenantByNamespaceGatewayImpl(private val tenantRepository: TenantRepository,
                                      private val clientRepository: ClientRepository): GetTenantByNamespaceGateway {

    override fun execute(namespace: String): TenantDomain {

        if (!this.tenantRepository.existsByNamespace(namespace)) {
            throw TenantNotFoundException("Tenant with namespace $namespace does not exist." )
        }

        val tenantDb = this.tenantRepository.findByNamespace(namespace)
        val keycloakClientsDomains = this.clientRepository.findAll(tenantDb.keycloakRealm).stream()
                .map { keycloakClient -> KeycloakClientToClientDomainTranslator().translate(keycloakClient) }
                .collect(Collectors.toList())

        return TenantDBToTenantDomainTranslator().translate(tenantDb, keycloakClientsDomains)
    }
}
