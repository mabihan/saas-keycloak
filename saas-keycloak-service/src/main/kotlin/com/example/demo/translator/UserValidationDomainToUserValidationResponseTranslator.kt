package com.example.demo.translator

import com.example.demo.model.UserDomain
import com.example.demo.model.UserResponse
import com.example.demo.model.UserValidationDomain
import com.example.demo.model.UserValidationResponse
import java.time.Instant
import java.time.ZoneId
import java.util.*


class UserValidationDomainToUserValidationResponseTranslator {

    fun translate(userValidationDomain: UserValidationDomain): UserValidationResponse {

        return UserValidationResponse(
            valid = userValidationDomain.valid,
            message = userValidationDomain.message.orEmpty(),
            error = userValidationDomain.error.orEmpty(),
        )
    }

}
