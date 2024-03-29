/*
 * Project SmartREST
 * Copyright (c) Alessio Saltarin 2022-23
 * This software is licensed under MIT License (see LICENSE)
 */

package net.littlelite.smartrest.dto

import net.littlelite.smartrest.model.Group
import net.littlelite.smartrest.model.Person

data class NewPersonDTO(
    val name: String,
    val surname: String,
    val email: String,
    val group: Group
)
{
    fun toPerson(): Person
    {
        return Person(
            name,
            surname,
            email,
            group
        )
    }
}