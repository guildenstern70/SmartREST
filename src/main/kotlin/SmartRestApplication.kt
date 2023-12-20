/*
 * Project SmartREST
 * Copyright (c) Alessio Saltarin 2022-23
 * This software is licensed under MIT License (see LICENSE)
 */

package net.littlelite.smartrest

import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.core.env.Environment

@SpringBootApplication
class SmartRestApplication(private val environment: Environment) : CommandLineRunner
{
    private val logger = LoggerFactory.getLogger(SmartRestApplication::class.java)

    private fun hello() {
        val runningUrl = "http://localhost:" + this.environment.getProperty("local.server.port")
        logger.info("**********************************************")
        logger.info("  SmartREST v." + SmartRestApplication.VERSION)
        logger.info("  Running on " + runningUrl + " (JVM " + System.getProperty("java.version") + ")")
        logger.info("**********************************************")
    }
    override fun run(vararg args: String?)
    {
        this.hello()
    }

    companion object
    {
        const val VERSION = "0.4.5"
    }
}

fun main(args: Array<String>) {
    runApplication<SmartRestApplication>(*arrayOf(*args))
}
