package com.example.demo.translator

import com.example.demo.model.UserCreateDomain
import com.example.demo.model.UserDomain
import com.example.demo.model.UserRequest

class UserRequestToUserCreateDomainTranslator {

    fun translate(userRequest: UserRequest): UserCreateDomain {
        return UserCreateDomain(
            firstName = userRequest.firstName,
            lastName = userRequest.lastName,
            username = userRequest.username,
            email = userRequest.email,
            password = userRequest.password,
        )
    }

}
