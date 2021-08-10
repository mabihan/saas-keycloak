package com.example.demo.gateway.keycloak.impl.user

import com.example.demo.exception.user.UserNotFoundException
import com.example.demo.gateway.database.repository.TenantRepository
import com.example.demo.gateway.keycloak.repository.UserRepository
import com.example.demo.gateway.keycloak.translator.KeycloakUserToUserDomainTranslator
import com.example.demo.model.UserDomain
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import javax.inject.Named

@Named
class SearchUserGatewayImpl(private val tenantRepository: TenantRepository,
                            private val userRepository: UserRepository) {

    fun execute(tenantNamespace: String, username: String?, email: String?, pageable: Pageable): Page<UserDomain> {

        try {
            val tenantDb = this.tenantRepository.findByNamespace(tenantNamespace)

            if (username != null) {
                if (username.isNotEmpty()) {
                    return this.userRepository.findByUsername(tenantDb.keycloakRealm, username, pageable)
                        .map(KeycloakUserToUserDomainTranslator()::translate)
                }
            }

            if (email != null) {
                if (email.isNotEmpty()) {
                    return this.userRepository.findByUsername(tenantDb.keycloakRealm, email, pageable)
                        .map(KeycloakUserToUserDomainTranslator()::translate)
                }
            }
            return Page.empty()
        } catch (ex: EmptyResultDataAccessException) {
            throw UserNotFoundException("Tenant $tenantNamespace not found." )
        }
    }
}
