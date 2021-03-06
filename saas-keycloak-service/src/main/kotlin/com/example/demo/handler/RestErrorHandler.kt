package com.example.demo.handler

import com.example.demo.exception.AlreadyExistException
import com.example.demo.exception.BadRequestException
import com.example.demo.exception.InternalErrorException
import com.example.demo.exception.tenant.TenantAlreadyExistException
import com.example.demo.exception.tenant.TenantCreationRequestInvalidException
import com.example.demo.exception.tenant.TenantNotFoundException
import com.example.demo.exception.tenant.TenantUuidMalformedException
import com.example.demo.exception.user.UserAlreadyExistException
import com.example.demo.exception.user.UserNotFoundException
import com.example.demo.model.MessageResponse
import com.example.demo.model.ProductHttpResponse
import com.example.demo.model.TenantHttpResponse
import com.example.demo.model.UserHttpResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.util.function.Consumer
import javax.validation.ConstraintViolation
import javax.validation.ConstraintViolationException

@RestControllerAdvice
class RestErrorHandler {

    private val log: Logger = LoggerFactory.getLogger(RestErrorHandler::class.java)

    @ExceptionHandler(HttpMessageNotReadableException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleHttpMessageNotReadableException(exception: HttpMessageNotReadableException): MessageResponse? {
        val errorList = ArrayList<String>()
        errorList.add(ProductHttpResponse.PRODUCT_BAD_REQUEST.httpMessage)

        return MessageResponse(ProductHttpResponse.PRODUCT_BAD_REQUEST.httpStatus,
                ProductHttpResponse.PRODUCT_BAD_REQUEST.httpMessage, errorList)
    }

    @ExceptionHandler(MissingServletRequestParameterException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleMissingServletRequestParameterException(exception: MissingServletRequestParameterException): MessageResponse? {
        val errorList = ArrayList<String>()
        errorList.add(exception.message)

        return MessageResponse(ProductHttpResponse.PRODUCT_BAD_REQUEST.httpStatus,
                ProductHttpResponse.PRODUCT_BAD_REQUEST.httpMessage, errorList)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleMethodArgumentNotValidException(exception: MethodArgumentNotValidException): MessageResponse? {
        val errorList = ArrayList<String>()
        exception.bindingResult.fieldErrors.forEach(Consumer { fieldError: FieldError -> errorList.add(fieldError.defaultMessage!!) })

        return MessageResponse(ProductHttpResponse.PRODUCT_BAD_REQUEST.httpStatus,
                ProductHttpResponse.PRODUCT_BAD_REQUEST.httpMessage, errorList)
    }

    @ExceptionHandler(ConstraintViolationException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleConstraintViolationException(exception: ConstraintViolationException): MessageResponse? {
        val errorList = ArrayList<String>()
        exception.constraintViolations.forEach(Consumer { violation: ConstraintViolation<*> -> errorList.add(violation.message) })

        return MessageResponse(ProductHttpResponse.PRODUCT_BAD_REQUEST.httpStatus,
                ProductHttpResponse.PRODUCT_BAD_REQUEST.httpMessage, errorList)
    }

    @ExceptionHandler(BadRequestException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleBadRequestException(exception: BadRequestException): MessageResponse? {
        val errorList = ArrayList<String>()
        errorList.add(exception.message!!)

        return MessageResponse(ProductHttpResponse.PRODUCT_BAD_REQUEST.httpStatus,
                ProductHttpResponse.PRODUCT_BAD_REQUEST.httpMessage, errorList)
    }

    @ExceptionHandler(AlreadyExistException::class)
    @ResponseStatus(HttpStatus.ALREADY_REPORTED)
    fun handleProductAlreadyExistException(exception: AlreadyExistException): MessageResponse? {
        val errorList = ArrayList<String>()
        errorList.add(exception.message!!)

        return MessageResponse(ProductHttpResponse.PRODUCT_ALREADY_EXIST.httpStatus,
                ProductHttpResponse.PRODUCT_ALREADY_EXIST.httpMessage, errorList)
    }

    @ExceptionHandler(InternalErrorException::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleProductInternalErrorException(exception: InternalErrorException): MessageResponse? {
        val errorList = ArrayList<String>()
        errorList.add(exception.message!!)

        return MessageResponse(ProductHttpResponse.PRODUCT_INTERNAL_ERROR.httpStatus,
                ProductHttpResponse.PRODUCT_INTERNAL_ERROR.httpMessage, errorList)
    }

    @ExceptionHandler(Throwable::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleGeneralInternalException(exception: Throwable): MessageResponse? {
        log.error("Internal Error: {}", exception.message, exception)

        val errorList = ArrayList<String>()
        errorList.add(ProductHttpResponse.PRODUCT_INTERNAL_ERROR.httpMessage)

        return MessageResponse(ProductHttpResponse.PRODUCT_INTERNAL_ERROR.httpStatus,
                ProductHttpResponse.PRODUCT_INTERNAL_ERROR.httpMessage, errorList)
    }

    @ExceptionHandler(TenantNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleTenantNotFoundException(exception: TenantNotFoundException): MessageResponse? {
        val errorList = ArrayList<String>()
        errorList.add(exception.message!!)

        return MessageResponse(TenantHttpResponse.TENANT_NOT_FOUND.httpStatus,
            TenantHttpResponse.TENANT_NOT_FOUND.httpMessage, errorList)
    }

    @ExceptionHandler(TenantAlreadyExistException::class)
    @ResponseStatus(HttpStatus.CONFLICT)
    fun handleTenantNotFoundException(exception: TenantAlreadyExistException): MessageResponse? {
        val errorList = ArrayList<String>()
        errorList.add(exception.message!!)

        return MessageResponse(TenantHttpResponse.TENANT_ALREADY_EXIST.httpStatus,
            TenantHttpResponse.TENANT_ALREADY_EXIST.httpMessage, errorList)
    }

    @ExceptionHandler(TenantCreationRequestInvalidException::class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    fun handleTenantNotFoundException(exception: TenantCreationRequestInvalidException): MessageResponse? {
        val errorList = ArrayList<String>()
        errorList.add(exception.message!!)

        return MessageResponse(TenantHttpResponse.TENANT_NOT_ACCEPTABLE.httpStatus,
            TenantHttpResponse.TENANT_NOT_ACCEPTABLE.httpMessage, errorList)
    }

    @ExceptionHandler(TenantUuidMalformedException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleTenantNotFoundException(exception: TenantUuidMalformedException): MessageResponse? {
        val errorList = ArrayList<String>()
        errorList.add(exception.message!!)

        return MessageResponse(TenantHttpResponse.TENANT_BAD_REQUEST.httpStatus,
            TenantHttpResponse.TENANT_BAD_REQUEST.httpMessage, errorList)
    }

    @ExceptionHandler(UserNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleUserNotFoundException(exception: UserNotFoundException): MessageResponse? {
        val errorList = ArrayList<String>()
        errorList.add(exception.message!!)

        return MessageResponse(UserHttpResponse.USER_NOT_FOUND.httpStatus,
            UserHttpResponse.USER_NOT_FOUND.httpMessage, errorList)
    }

    @ExceptionHandler(UserAlreadyExistException::class)
    @ResponseStatus(HttpStatus.CONFLICT)
    fun handleUserAlreadyExistException(exception: UserAlreadyExistException): MessageResponse? {
        val errorList = ArrayList<String>()
        errorList.add(exception.message!!)

        return MessageResponse(UserHttpResponse.USER_ALREADY_EXIST.httpStatus,
            UserHttpResponse.USER_ALREADY_EXIST.httpMessage, errorList)
    }
}
