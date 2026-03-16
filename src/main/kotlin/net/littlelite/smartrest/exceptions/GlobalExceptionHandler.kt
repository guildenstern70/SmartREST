/*
 * Project SmartREST
 * Copyright (c) Alessio Saltarin 2022-26
 * This software is licensed under MIT License (see LICENSE)
 */

package net.littlelite.smartrest.exceptions

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.OffsetDateTime

@RestControllerAdvice
class GlobalExceptionHandler
{
    @ExceptionHandler(ResourceNotFound::class)
    fun handleResourceNotFound(ex: ResourceNotFound, request: HttpServletRequest): ProblemDetail
    {
        return buildProblemDetail(
            status = HttpStatus.NOT_FOUND,
            title = "Resource not found",
            detail = ex.message ?: "Requested resource was not found",
            request = request,
            errorCode = "RESOURCE_NOT_FOUND"
        )
    }

    @ExceptionHandler(ResourceAlreadyExists::class)
    fun handleResourceAlreadyExists(ex: ResourceAlreadyExists, request: HttpServletRequest): ProblemDetail
    {
        return buildProblemDetail(
            status = HttpStatus.CONFLICT,
            title = "Resource already exists",
            detail = ex.message ?: "Resource already exists",
            request = request,
            errorCode = "RESOURCE_ALREADY_EXISTS"
        )
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleInvalidPayload(ex: HttpMessageNotReadableException, request: HttpServletRequest): ProblemDetail
    {
        return buildProblemDetail(
            status = HttpStatus.BAD_REQUEST,
            title = "Invalid request body",
            detail = "Request body is missing or malformed",
            request = request,
            errorCode = "INVALID_REQUEST_BODY"
        )
    }

    @ExceptionHandler(Exception::class)
    fun handleUnexpectedError(ex: Exception, request: HttpServletRequest): ProblemDetail
    {
        return buildProblemDetail(
            status = HttpStatus.INTERNAL_SERVER_ERROR,
            title = "Unexpected error",
            detail = "An unexpected error occurred",
            request = request,
            errorCode = "INTERNAL_SERVER_ERROR"
        )
    }

    private fun buildProblemDetail(
        status: HttpStatus,
        title: String,
        detail: String,
        request: HttpServletRequest,
        errorCode: String
    ): ProblemDetail
    {
        val problemDetail = ProblemDetail.forStatusAndDetail(status, detail)
        problemDetail.title = title
        problemDetail.instance = java.net.URI.create(request.requestURI)
        problemDetail.setProperty("path", request.requestURI)
        problemDetail.setProperty("errorCode", errorCode)
        problemDetail.setProperty("timestamp", OffsetDateTime.now().toString())
        return problemDetail
    }
}


