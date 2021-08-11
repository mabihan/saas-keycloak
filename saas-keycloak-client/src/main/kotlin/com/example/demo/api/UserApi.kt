package com.example.demo.api

import com.example.demo.model.*
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.concurrent.CompletionStage
import javax.validation.Valid
import javax.validation.constraints.Email

@RequestMapping("/api/v1/tenant/")
@Api(tags = ["User"])
interface UserApi {

    @ApiOperation(value = "Create a user", notes = "Create a user.")
    @ApiResponses(value = [ApiResponse(code = 201, message = "Created", response = MessageResponse::class),
        ApiResponse(code = 208, message = "User already exist", response = MessageResponse::class),
        ApiResponse(code = 400, message = "Bad request", response = MessageResponse::class),
        ApiResponse(code = 500, message = "Internal error to create the user", response = MessageResponse::class)])
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{tenantUuid}/user/")
    fun createUser(@PathVariable tenantUuid: String, @Valid @RequestBody userRequest: UserRequest): CompletionStage<UserResponse>

    @ApiOperation(value = "Update a user", notes = "Update an existing user.")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{tenantUuid}/user")
    fun updateUser(@PathVariable tenantUuid: String, @Valid @RequestBody userRequest: UserRequest): CompletionStage<UserResponse>

    @ApiOperation(value = "Get user details", notes = "Get user details.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{tenantUuid}/user//{id}")
    fun getUser(@PathVariable tenantUuid: String, @PathVariable id: String): CompletionStage<UserResponse>

    @ApiOperation(value = "Get username validity", notes = "Get username validity.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{tenantUuid}/user/username-validity")
    fun getUsernameValidity(@PathVariable tenantUuid: String, username: String): CompletionStage<ObjectValidationResponse>

    @ApiOperation(value = "Get email validity", notes = "Get email validity.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{tenantUuid}/user/email-validity")
    fun getEmailValidity(@PathVariable tenantUuid: String, email: String): CompletionStage<ObjectValidationResponse>

    @ApiOperation(value = "Get all users", notes = "Get all from tenant.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{tenantUuid}/users")
    fun getAllUsers(@PathVariable tenantUuid: String, size: Int, page: Int): Any
}
