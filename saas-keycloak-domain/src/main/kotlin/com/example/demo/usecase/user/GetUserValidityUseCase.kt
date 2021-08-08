package com.example.demo.usecase.user

import com.example.demo.gateway.keycloak.user.DoesUserExistGateway
import com.example.demo.model.UserValidationDomain
import javax.inject.Named

@Named
class GetUserValidityUseCase(private val doesUserExistGateway: DoesUserExistGateway) {


    fun execute(tenantNamespace: String, username: String?, email: String?): UserValidationDomain {

        val valid: Boolean
        val message: String
        val error: String

        val userAlreadyExist = this.doesUserExistGateway.execute(tenantNamespace, username, email)

        if (userAlreadyExist) {
            valid = false
            message = ""
            error = "A user already using exist with these credentials."
        } else {
            valid = true
            message = "User looks good!"
            error = ""
        }

        return UserValidationDomain(
            valid = valid,
            message = message,
            error = error
        )
    }
}
