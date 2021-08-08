package com.example.demo.controller

import com.example.demo.api.UserApi
import com.example.demo.model.*
import com.example.demo.translator.TenantDomainToTenantResponseTranslator
import com.example.demo.translator.UserDomainToUserResponseTranslator
import com.example.demo.translator.UserRequestToUserCreateDomainTranslator
import com.example.demo.usecase.user.CreateUserUseCase
import com.example.demo.usecase.user.GetAllUsersUseCase
import com.example.demo.usecase.user.GetUserUseCase
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
                     private val getAllUsersUseCase: GetAllUsersUseCase): UserApi {

    private val log: Logger = LoggerFactory.getLogger(UserController::class.java)

    override fun createUser(tenantNamespace: String, userRequest: UserRequest): MessageResponse {
        log.debug("User API starting to creating the tenantNamespace: {}", userRequest.username)

        val userCreateDomain = UserRequestToUserCreateDomainTranslator().translate(userRequest)

        createUserUseCase.execute(tenantNamespace, userCreateDomain)

        return MessageResponse(UserHttpResponse.USER_CREATED.httpStatus, UserHttpResponse.USER_CREATED.httpMessage)
    }

    override fun updateUser(tenantNamespace: String, userRequest: UserRequest): MessageResponse {
        TODO("Not yet implemented")
    }

    override fun getUser(tenantNamespace: String, id: String): CompletionStage<UserResponse> {
        return CompletableFuture
            .supplyAsync { this.getUserUseCase.execute(tenantNamespace,id)}
            .thenApplyAsync { UserDomainToUserResponseTranslator().translate(it) }
    }

    override fun getUserValidity(tenantNamespace: String, username: String?, email: String?): CompletionStage<UserValidationResponse> {
        TODO("Not yet implemented")
    }

    override fun getAllUsers(tenantNamespace: String, size: Int, page: Int): Page<UserResponse> {
        log.debug("User API starting to list all users for tenantNamespace: {}", tenantNamespace)

        val pageRequest = PageRequest.of(page, size)

        return this.getAllUsersUseCase.execute(tenantNamespace, pageRequest).map(UserDomainToUserResponseTranslator()::translate)
    }
}
