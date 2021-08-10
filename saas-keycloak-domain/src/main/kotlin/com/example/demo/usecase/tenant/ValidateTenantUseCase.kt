package com.example.demo.usecase.tenant

import com.example.demo.exception.tenant.TenantUuidMalformedException
import com.example.demo.gateway.tenant.DoesTenantNamespaceExistGateway
import com.example.demo.gateway.tenant.DoesTenantUuidExistGateway
import com.example.demo.model.ObjectValidationDomain
import java.util.regex.Pattern
import javax.inject.Named

@Named
class ValidateTenantUseCase(private val doesTenantNamespaceExistGateway: DoesTenantNamespaceExistGateway) {

    private val minNamespaceLength = 3

    fun execute(namespace: String): ObjectValidationDomain {

        val valid: Boolean
        val message: String
        val error: String

        val doesNamespaceAlreadyExist = doesTenantNamespaceExistGateway.execute(namespace)
        val isNamespaceLongEnough = namespace.length < this.minNamespaceLength
        val isNamespaceUsingAllowedCharacters = !namespace.matches(Pattern.compile("^[a-zA-Z0-9-_ ]*$").toRegex())

        if (doesNamespaceAlreadyExist) {
            valid = false
            message = ""
            error = "This name is not available."
        } else if (isNamespaceLongEnough) {
            valid = false
            message = ""
            error = "The name must me at least ${this.minNamespaceLength} characters long."
        } else if (isNamespaceUsingAllowedCharacters) {
            valid = false
            message = ""
            error = "Only alphanumeric characters, underscores and hyphens are allowed in the name."
        } else {
            valid = true
            message = "This name looks great!"
            error = ""
        }

        return ObjectValidationDomain(
            valid = valid,
            message = message,
            error = error
        )

    }
}
