package com.example.demo.model

import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime

data class TenantResponse(
        @ApiModelProperty(required = true)
        val namespace: String,

        @ApiModelProperty(required = true)
        val contact: String,

        @JsonFormat(pattern = "YYYY-MM-dd'T'HH:mm:ss")
        val createdDate: LocalDateTime,
)
