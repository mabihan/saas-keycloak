package com.example.demo.model

import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class UserRequest(
        @ApiModelProperty(required = false)
        val firstName: String?,

        @ApiModelProperty(required = false)
        val lastName: String?,

        // TODO : Add custom validator to check email uniqueness
        @ApiModelProperty(required = true)
        @field:NotBlank(message = "The contact email must be informed")
        @field:Email(message = "The contact must be a valid email")
        val email: String,

        // TODO : Add custom validator to check username uniqueness
        @ApiModelProperty(required = true)
        @field:NotBlank(message = "The username must be informed")
        @field:Size(min = 2, max = 255, message = "The min username name size is 2 and the max is 255")
        val username: String,

        @ApiModelProperty(required = true)
        @field:NotBlank(message = "The password must be informed")
        @field:Size(min = 2, max = 255, message = "The min password name size is 2 and the max is 255")
        val password: String,

        )
