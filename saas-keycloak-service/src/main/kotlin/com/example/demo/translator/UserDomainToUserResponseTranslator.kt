package com.example.demo.translator

import com.example.demo.model.UserDomain
import com.example.demo.model.UserResponse
import java.time.Instant
import java.time.ZoneId
import java.util.*


class UserDomainToUserResponseTranslator {

    fun translate(userDomain: UserDomain, tenantUuid: String): UserResponse {

        val instant = Instant.ofEpochMilli(userDomain.createdTimestamp)
        val createdTimestamp = instant.atZone(ZoneId.systemDefault()).toLocalDateTime()

        return UserResponse(
            uuid = userDomain.id.orEmpty(),
            tenantUuid = tenantUuid,
            firstName = userDomain.firstName,
            lastName = userDomain.lastName,
            email = userDomain.email,
            username = userDomain.username,
            createdTimestamp = createdTimestamp,
            enabled = userDomain.enabled,
            totp = userDomain.totp,
            emailVerified = userDomain.emailVerified
        )
    }

}
