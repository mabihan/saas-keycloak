package com.example.demo.model

enum class UserHttpResponse(val httpStatus: Int, val httpMessage: String) {

    USER_CREATED(201, "created"),
    USER_DELETED(200, "deleted"),
    USER_UPDATED(202, "updated"),
    USER_ALREADY_EXIST(208, "already exist"),
    USER_NOT_FOUND(404, "not found"),
    USER_BAD_REQUEST(400, "bad request"),
    USER_INTERNAL_ERROR(500, "internal error")

}
