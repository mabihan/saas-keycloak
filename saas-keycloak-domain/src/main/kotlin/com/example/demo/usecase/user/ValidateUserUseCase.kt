package com.example.demo.usecase.user

import com.example.demo.gateway.keycloak.user.DoesUserExistGateway
import com.example.demo.model.ObjectValidationDomain
import java.util.regex.Pattern
import javax.inject.Named

@Named
class ValidateUserUseCase(private val doesUserExistGateway: DoesUserExistGateway) {

    fun executeForEmail(tenantUuid: String, email: String): ObjectValidationDomain {

        val valid: Boolean
        val message: String
        val error: String

        val userAlreadyExist = this.doesUserExistGateway.executeForEmail(tenantUuid, email)
        val isEmailFormatValid = !email.matches(Pattern.compile("^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*\$").toRegex())

        if (userAlreadyExist) {
            valid = false
            message = ""
            error = "This email is already used. Try to login or reset your password."
        } else if (isEmailFormatValid) {
            valid = false
            message = ""
            error = "This email is not valid."
        } else {
            valid = true
            message = "Emails looks good!"
            error = ""
        }

        return ObjectValidationDomain(
            valid = valid,
            message = message,
            error = error
        )
    }

    fun executeForUuid(tenantUuid: String, uuid: String): ObjectValidationDomain {

        val valid: Boolean
        val message: String
        val error: String

        val userAlreadyExist = this.doesUserExistGateway.executeForUuid(tenantUuid, uuid)

        if (userAlreadyExist) {
            valid = false
            message = ""
            error = "This uuid is not available."
        } else {
            valid = true
            message = "Uuid looks good!"
            error = ""
        }

        return ObjectValidationDomain(
            valid = valid,
            message = message,
            error = error
        )
    }

    fun executeForUsername(tenantUuid: String, username: String): ObjectValidationDomain {

        val valid: Boolean
        val message: String
        val error: String

        val userAlreadyExist = this.doesUserExistGateway.executeForUsername(tenantUuid, username)

        if (userAlreadyExist) {
            valid = false
            message = ""
            error = "This username is not available."
        } else {
            valid = true
            message = "Username looks good!"
            error = ""
        }

        return ObjectValidationDomain(
            valid = valid,
            message = message,
            error = error
        )
    }
}
