package com.example.demo.model

import io.swagger.annotations.ApiModelProperty
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class ProductRequest(
        @ApiModelProperty(required = true)
        @field:NotBlank(message = "The product name must be informed")
        @field:Size(min = 2, max = 255, message = "The min product name size is 2 and the max is 255")
        val productName: String,

        @ApiModelProperty(required = true)
        @field:NotBlank(message = "The unit type must be informed")
        @field:Size(max = 20, message = "The max unit type size is 20 characters")
        val unitType: String
)