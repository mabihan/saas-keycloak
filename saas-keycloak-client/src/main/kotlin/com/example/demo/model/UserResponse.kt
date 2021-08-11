package com.example.demo.model

import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime

data class UserResponse(
        @ApiModelProperty(required = true)
        val uuid: String,

        @ApiModelProperty(required = true)
        val tenantUuid: String,

        @ApiModelProperty(required = true)
        val firstName: String,

        @ApiModelProperty(required = true)
        val lastName: String,

        @ApiModelProperty(required = true)
        val email: String,

        @ApiModelProperty(required = true)
        val username: String,

        @ApiModelProperty(required = true)
        val createdTimestamp: LocalDateTime,

        @ApiModelProperty(required = false)
        val enabled: Boolean?,

        @ApiModelProperty(required = false)
        val totp: Boolean?,

        @ApiModelProperty(required = false)
        val emailVerified: Boolean?,
)
