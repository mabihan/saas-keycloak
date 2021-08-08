package com.example.demo.api

import com.example.demo.model.MessageResponse
import com.example.demo.model.UserRequest
import com.example.demo.model.UserResponse
import com.example.demo.model.UserValidationResponse
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.concurrent.CompletionStage
import javax.validation.Valid
import javax.validation.constraints.Email

@RequestMapping("/v1/")
@Api(tags = ["User"])
interface UserApi {

    @ApiOperation(value = "Create a user", notes = "Create a user.")
    @ApiResponses(value = [ApiResponse(code = 201, message = "Created", response = MessageResponse::class),
        ApiResponse(code = 208, message = "User already exist", response = MessageResponse::class),
        ApiResponse(code = 400, message = "Bad request", response = MessageResponse::class),
        ApiResponse(code = 500, message = "Internal error to create the user", response = MessageResponse::class)])
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{tenantNamespace}/user/")
    fun createUser(@PathVariable tenantNamespace: String, @Valid @RequestBody userRequest: UserRequest): MessageResponse

    @ApiOperation(value = "Update a user", notes = "Update an existing user.")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{tenantNamespace}/user")
    fun updateUser(@PathVariable tenantNamespace: String, @Valid @RequestBody userRequest: UserRequest): MessageResponse

    @ApiOperation(value = "Get user details", notes = "Get user details.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{tenantNamespace}/user//{id}")
    fun getUser(@PathVariable tenantNamespace: String, @PathVariable id: String): CompletionStage<UserResponse>

    @ApiOperation(value = "Get username or email validity", notes = "Get username or email validity.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{tenantNamespace}/user/validity")
    fun getUserValidity(@PathVariable tenantNamespace: String, username: String?, email: String?): CompletionStage<UserValidationResponse>

    @ApiOperation(value = "Get all users", notes = "Get all from tenant.")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{tenantNamespace}/users")
    fun getAllUsers(@PathVariable tenantNamespace: String, size: Int, page: Int): Any
}
