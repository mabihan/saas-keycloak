package com.example.demo.gateway.keycloak.impl.user

import com.example.demo.exception.tenant.TenantNotFoundException
import com.example.demo.exception.tenant.TenantUuidMalformedException
import com.example.demo.gateway.database.repository.TenantRepository
import com.example.demo.gateway.keycloak.repository.UserRepository
import com.example.demo.gateway.keycloak.translator.KeycloakUserToUserDomainTranslator
import com.example.demo.gateway.keycloak.user.GetAllUsersGateway
import com.example.demo.model.UserDomain
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*
import javax.inject.Named

@Named
class GetAllUsersGatewayImpl(private val tenantRepository: TenantRepository,
                             private val userRepository: UserRepository): GetAllUsersGateway {

    override fun execute(tenantUuid: String, pageable: Pageable): Page<UserDomain> {


        try {
            val validUuid = UUID.fromString(tenantUuid)

            try {
                val tenantDb = this.tenantRepository.findByUuid(validUuid)

                return this.userRepository.findAll(realm = tenantDb.keycloakRealm, pageable = pageable)
                    .map(KeycloakUserToUserDomainTranslator()::translate)

            } catch (ex: EmptyResultDataAccessException) {
                throw TenantNotFoundException("Tenant $tenantUuid not found." )
            }
        } catch (ex: IllegalArgumentException ) {
            throw TenantUuidMalformedException("$tenantUuid is not a valid UUID")
        }
    }
}
