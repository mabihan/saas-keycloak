package com.example.demo.model

import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class TenantRequest(
        @ApiModelProperty(required = true)
        @field:NotBlank(message = "The namespace must be informed")
        @field:Size(min = 2, max = 255, message = "The min namespace name size is 2 and the max is 255")
        val namespace: String,

        @ApiModelProperty(required = true)
        @field:NotBlank(message = "The contact email must be informed")
        @field:Email(message = "The contact must be a valid email")
        val email: String,

        )
