/*
 * Project SmartREST
 * Copyright (c) Alessio Saltarin 2022-26
 * This software is licensed under MIT License (see LICENSE)
 */

package net.littlelite.smartrest

import net.littlelite.smartrest.exceptions.GlobalExceptionHandler
import net.littlelite.smartrest.exceptions.ResourceAlreadyExists
import net.littlelite.smartrest.exceptions.ResourceNotFound
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import org.springframework.mock.web.MockHttpServletRequest

internal class PersonControllerExceptionStatusTests
{
    private val handler = GlobalExceptionHandler()

    @Test
    fun `ResourceNotFound is mapped to 404 ProblemDetail`()
    {
        val request = MockHttpServletRequest("GET", "/api/v1/persons/999999")

        val response = handler.handleResourceNotFound(ResourceNotFound(999999L), request)

        assertThat(response.status).isEqualTo(HttpStatus.NOT_FOUND.value())
        assertThat(response.title).isEqualTo("Resource not found")
        assertThat(response.properties?.get("path")).isEqualTo("/api/v1/persons/999999")
        assertThat(response.properties?.get("errorCode")).isEqualTo("RESOURCE_NOT_FOUND")
        assertThat(response.instance?.path).isEqualTo("/api/v1/persons/999999")
    }

    @Test
    fun `ResourceAlreadyExists is mapped to 409 ProblemDetail`()
    {
        val request = MockHttpServletRequest("POST", "/api/v1/persons")

        val response = handler.handleResourceAlreadyExists(ResourceAlreadyExists("alessiosaltarin@gmail.com"), request)

        assertThat(response.status).isEqualTo(HttpStatus.CONFLICT.value())
        assertThat(response.title).isEqualTo("Resource already exists")
        assertThat(response.properties?.get("path")).isEqualTo("/api/v1/persons")
        assertThat(response.properties?.get("errorCode")).isEqualTo("RESOURCE_ALREADY_EXISTS")
        assertThat(response.instance?.path).isEqualTo("/api/v1/persons")
    }
}



