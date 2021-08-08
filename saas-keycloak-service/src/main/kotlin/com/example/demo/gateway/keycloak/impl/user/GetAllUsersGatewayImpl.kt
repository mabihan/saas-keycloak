package com.example.demo.gateway.keycloak.impl.user

import com.example.demo.exception.tenant.TenantNotFoudException
import com.example.demo.gateway.database.repository.TenantRepository
import com.example.demo.gateway.keycloak.repository.UserRepository
import com.example.demo.gateway.keycloak.translator.KeycloakUserToUserDomainTranslator
import com.example.demo.gateway.keycloak.user.GetAllUsersGateway
import com.example.demo.model.UserDomain
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import javax.inject.Named

@Named
class GetAllUsersGatewayImpl(private val tenantRepository: TenantRepository,
                             private val userRepository: UserRepository): GetAllUsersGateway {

    override fun execute(tenantNamespace: String, pageable: Pageable): Page<UserDomain> {

        try {
            val tenantDb = this.tenantRepository.findByNamespace(tenantNamespace)

            return this.userRepository.findAll(realm = tenantDb.keycloakRealm, pageable = pageable)
                .map(KeycloakUserToUserDomainTranslator()::translate)

        } catch (ex: EmptyResultDataAccessException) {
            throw TenantNotFoudException("Tenant $tenantNamespace not found." )
        }
    }
}
