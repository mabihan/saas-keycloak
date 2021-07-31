package com.example.demo.model

import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime

data class ProductResponse(
        @ApiModelProperty(required = true)
        val productName: String,

        @ApiModelProperty(required = true)
        val unitType: String,

        @JsonFormat(pattern = "YYYY-MM-dd'T'HH:mm:ss")
        val createdDate: LocalDateTime,
)