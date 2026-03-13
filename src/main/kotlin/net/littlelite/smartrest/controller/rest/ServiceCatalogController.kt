/*
 * Project SmartREST
 * Copyright (c) Alessio Saltarin 2022-23
 * This software is licensed under MIT License (see LICENSE)
 */

package net.littlelite.smartrest.controller.rest

import tools.jackson.databind.ObjectMapper
import net.littlelite.smartrest.SmartRestApplication
import org.slf4j.LoggerFactory
import org.springframework.http.ContentDisposition
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping
import java.util.TreeMap

@RestController
@RequestMapping("/api/v1/catalog")
class ServiceCatalogController(
    private val requestMappingHandlerMapping: RequestMappingHandlerMapping,
    private val objectMapper: ObjectMapper
)
{
    private val logger = LoggerFactory.getLogger(ServiceCatalogController::class.java)

    @GetMapping("/openapi/download", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun downloadOpenApiCatalog(): ResponseEntity<ByteArray>
    {
        logger.info("Generating OpenAPI service catalog from Spring mappings")

        val paths = TreeMap<String, MutableMap<String, Map<String, String>>>()

        requestMappingHandlerMapping.handlerMethods.forEach { (info, method) ->
            val patternValues = info.patternValues
            val methods = info.methodsCondition.methods

            if (patternValues.isEmpty() || methods.isEmpty())
            {
                return@forEach
            }

            patternValues.forEach { pattern ->
                val operations = paths.computeIfAbsent(pattern) { TreeMap() }
                methods.forEach { httpMethod ->
                    operations[httpMethod.name.lowercase()] = mapOf(
                        "operationId" to "${method.beanType.simpleName}_${method.method.name}",
                        "summary" to "${method.beanType.simpleName}.${method.method.name}"
                    )
                }
            }
        }

        val openApiCatalog = linkedMapOf(
            "openapi" to "3.0.3",
            "info" to mapOf(
                "title" to "SmartREST Service Catalog",
                "version" to SmartRestApplication.VERSION
            ),
            "paths" to paths
        )

        val docsBody = objectMapper.writeValueAsBytes(openApiCatalog)

        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        headers.contentDisposition = ContentDisposition.attachment()
            .filename("openapi-service-catalog.json")
            .build()

        return ResponseEntity(docsBody, headers, HttpStatus.OK)
    }
}



