package com.example.demo.model

enum class ProductHttpResponse(val httpStatus: Int, val httpMessage: String) {

    PRODUCT_CREATED(201, "created"),
    PRODUCT_UPDATED(202, "updated"),
    PRODUCT_ALREADY_EXIST(208, "already exist"),
    PRODUCT_NOT_FOUND(404, "not found"),
    PRODUCT_BAD_REQUEST(400, "bad request"),
    PRODUCT_INTERNAL_ERROR(500, "internal error")

}