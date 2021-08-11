package com.example.demo.controller

import com.example.demo.api.UserApi
import com.example.demo.model.*
import com.example.demo.translator.ObjectValidationDomainToObjectValidationResponseTranslator
import com.example.demo.translator.UserDomainToUserResponseTranslator
import com.example.demo.translator.UserRequestToUserCreateDomainTranslator
import com.example.demo.usecase.user.CreateUserUseCase
import com.example.demo.usecase.user.GetAllUsersUseCase
import com.example.demo.usecase.user.GetUserUseCase
import com.example.demo.usecase.user.ValidateUserUseCase
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletionStage

@RestController
class UserController(private val createUserUseCase: CreateUserUseCase,
                     private val getUserUseCase: GetUserUseCase,
                     private val getAllUsersUseCase: GetAllUsersUseCase,
                     private val validateUserUseCase: ValidateUserUseCase): UserApi {

    private val log: Logger = LoggerFactory.getLogger(UserController::class.java)

    override fun createUser(tenantUuid: String, userRequest: UserRequest): CompletionStage<UserResponse> {
        log.debug("User API starting to creating the user: {}", userRequest.username)

        val userCreateDomain = UserRequestToUserCreateDomainTranslator().translate(userRequest)

        return CompletableFuture
            .supplyAsync { createUserUseCase.execute(tenantUuid, userCreateDomain)}
            .thenApplyAsync { UserDomainToUserResponseTranslator().translate(it, tenantUuid) }

    }

    override fun updateUser(tenantUuid: String, userRequest: UserRequest): CompletionStage<UserResponse> {
        TODO("Not yet implemented")
    }

    override fun getUser(tenantUuid: String, id: String): CompletionStage<UserResponse> {
        return CompletableFuture
            .supplyAsync { this.getUserUseCase.execute(tenantUuid,id)}
            .thenApplyAsync { UserDomainToUserResponseTranslator().translate(it, tenantUuid) }
    }

    override fun getUsernameValidity(tenantUuid: String, username: String): CompletionStage<ObjectValidationResponse> {
        return CompletableFuture
            .supplyAsync { this.validateUserUseCase.executeForUsername(tenantUuid, username)}
            .thenApplyAsync { ObjectValidationDomainToObjectValidationResponseTranslator().translate(it) }
    }

    override fun getEmailValidity(tenantUuid: String, email: String): CompletionStage<ObjectValidationResponse> {
        return CompletableFuture
            .supplyAsync { this.validateUserUseCase.executeForEmail(tenantUuid, email)}
            .thenApplyAsync { ObjectValidationDomainToObjectValidationResponseTranslator().translate(it) }
    }

    override fun getAllUsers(tenantUuid: String, size: Int, page: Int): Page<UserResponse> {
        log.debug("User API starting to list all users for tenantUuid: {}", tenantUuid)

        val pageRequest = PageRequest.of(page, size)

        return this.getAllUsersUseCase.execute(tenantUuid, pageRequest)
            .map( fun(userDomain: UserDomain): UserResponse {
                return UserDomainToUserResponseTranslator().translate(userDomain, tenantUuid)
            })
    }
}
