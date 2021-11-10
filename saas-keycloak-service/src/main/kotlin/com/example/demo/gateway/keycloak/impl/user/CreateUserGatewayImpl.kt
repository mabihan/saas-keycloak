package com.example.demo.gateway.keycloak.impl.user

import com.example.demo.exception.InternalErrorException
import com.example.demo.exception.user.UserNotFoundException
import com.example.demo.gateway.database.repository.TenantRepository
import com.example.demo.gateway.keycloak.repository.RealmRepository
import com.example.demo.gateway.keycloak.repository.UserRepository
import com.example.demo.gateway.keycloak.translator.KeycloakUserToUserDomainTranslator
import com.example.demo.gateway.keycloak.translator.UserDomainToKeycloakUserCreateTranslator
import com.example.demo.gateway.keycloak.user.CreateUserGateway
import com.example.demo.model.UserCreateDomain
import com.example.demo.model.UserDomain
import javax.inject.Named

@Named
class CreateUserGatewayImpl(
    private val userRepository: UserRepository): CreateUserGateway {

    override fun execute(tenantNamespace: String, userCreateDomain: UserCreateDomain): UserDomain {

        val keycloakUserId =  this.userRepository.save(tenantNamespace, UserDomainToKeycloakUserCreateTranslator()
            .translate(userCreateDomain))

        if (keycloakUserId.isNotEmpty()) {
            this.userRepository.sendVerificationEmail(tenantNamespace, keycloakUserId)
            val keycloakUser = this.userRepository.findById(tenantNamespace, keycloakUserId)
            if (keycloakUser != null) {
                return KeycloakUserToUserDomainTranslator().translate(keycloakUser)
            } else {
                throw InternalErrorException("User $keycloakUserId not found after being created.")
            }
        } else {
            throw InternalErrorException("Internal error while creating user ${userCreateDomain.username}")
        }
    }
}
