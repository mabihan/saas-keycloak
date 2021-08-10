package com.example.demo.translator

import com.example.demo.model.ObjectValidationDomain
import com.example.demo.model.ObjectValidationResponse
import com.example.demo.model.UserValidationResponse


class ObjectValidationDomainToObjectValidationResponseTranslator {

    fun translate(objectValidationDomain: ObjectValidationDomain): ObjectValidationResponse {

        return ObjectValidationResponse(
            valid = objectValidationDomain.valid,
            message = objectValidationDomain.message.orEmpty(),
            error = objectValidationDomain.error.orEmpty(),
        )
    }

}
