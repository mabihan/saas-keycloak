package com.example.demo.gateway.keycloak.impl.user

import com.example.demo.exception.tenant.TenantNotFoundException
import com.example.demo.exception.tenant.TenantUuidMalformedException
import com.example.demo.exception.user.UserNotFoundException
import com.example.demo.gateway.database.repository.TenantRepository
import com.example.demo.gateway.keycloak.repository.UserRepository
import com.example.demo.gateway.keycloak.translator.KeycloakUserToUserDomainTranslator
import com.example.demo.gateway.keycloak.user.GetUserGateway
import com.example.demo.model.UserDomain
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.web.reactive.function.client.WebClientResponseException
import java.util.*
import javax.inject.Named

@Named
class GetUserGatewayImpl(
    private val tenantRepository: TenantRepository,
    private val userRepository: UserRepository
) : GetUserGateway {

    override fun execute(tenantUuid: String, id: String): UserDomain {

        try {
            val validUuid = UUID.fromString(tenantUuid)

            try {
                val tenantDb = this.tenantRepository.findByUuid(validUuid)

                try {
                    val keycloakUser = this.userRepository.findById(tenantDb.keycloakRealm, id)
                    if (keycloakUser != null) {
                        return KeycloakUserToUserDomainTranslator().translate(keycloakUser)
                    } else {
                        throw UserNotFoundException("User $id not found.")
                    }
                } catch (ex: WebClientResponseException.NotFound) {
                    throw UserNotFoundException("User $id not found.")
                }
            } catch (ex: EmptyResultDataAccessException) {
                throw TenantNotFoundException("User $tenantUuid not found.")
            }

        } catch (ex: IllegalArgumentException) {
            throw TenantUuidMalformedException("$tenantUuid is not a valid UUID")
        }

    }
}
