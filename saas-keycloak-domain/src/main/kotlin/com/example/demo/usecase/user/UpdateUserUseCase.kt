package com.example.demo.usecase.user

import com.example.demo.model.UserDomain
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Named

@Named
class UpdateUserUseCase {

    private val log: Logger = LoggerFactory.getLogger(UpdateUserUseCase::class.java)

    fun execute(userDomain: UserDomain) {}
}
