package com.example.demo.gateway.keycloak.impl.user

import com.example.demo.exception.InternalErrorException
import com.example.demo.gateway.keycloak.repository.UserRepository
import com.example.demo.gateway.keycloak.translator.UserDomainToKeycloakUserCreateTranslator
import com.example.demo.gateway.keycloak.user.CreateUserGateway
import com.example.demo.model.UserCreateDomain
import javax.inject.Named

@Named
class CreateUserGatewayImpl(private val userRepository: UserRepository): CreateUserGateway {

    override fun execute(tenantNamespace: String, userCreateDomain: UserCreateDomain) {

        val keycloakUserId =  this.userRepository.save(tenantNamespace, UserDomainToKeycloakUserCreateTranslator()
            .translate(userCreateDomain))

        if (keycloakUserId.isNotEmpty()) {
            this.userRepository.sendVerificationEmail(tenantNamespace, keycloakUserId)
        } else {
            throw InternalErrorException("Internal error while creating user ${userCreateDomain.username}")
        }
    }
}
