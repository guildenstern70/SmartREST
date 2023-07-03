/*
 * Project SmartREST
 * Copyright (c) Alessio Saltarin 2022-23
 * This software is licensed under MIT License (see LICENSE)
 */

package net.littlelite.smartrest.net.littlelite.smartrest.service

import net.littlelite.smartrest.dao.PersonDAO
import net.littlelite.smartrest.model.Group
import net.littlelite.smartrest.model.Person
import net.littlelite.smartrest.model.Phone
import net.littlelite.smartrest.model.Provider
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Service
import java.util.*


@Service
class DbInitializer @Autowired constructor(private val personDAO: PersonDAO) : ApplicationRunner
{
    override fun run(args: ApplicationArguments)
    {
        logger.info("Populating Database")
        val phones = listOf(
            Phone("348-39020292", Provider.TIM, null),
            Phone("333-32232211", Provider.VODAFONE, null)
        )
        val persons = arrayOf(
            Person(
                "Alessio", "Saltarin",
                "alessiosaltarin@gmail.com", Group.ADMINISTRATOR
            ),
            Person(
                "Laura", "Renzi",
                "laurarenzi@gmail.com", Group.USER
            ),
            Person(
                "Elena", "Santandrea",
                "elenasan@gmail.com", Group.USER
            ),
            Person(
                "Filippo", "Giusti",
                "filippogiuisti@outlook.com", Group.GUEST
            )
        )
        Arrays.stream(persons)
            .forEach { p: Person ->
                p.setPhones(phones)
                logger.info("Saving $p")
                personDAO.save(p)
            }
    }

    companion object
    {
        protected val logger: Logger = LoggerFactory.getLogger(DbInitializer::class.java)
    }
}

