/*
 * Project SmartREST
 * Copyright (c) Alessio Saltarin 2021.
 * This software is licensed under MIT License (see LICENSE)
 */

package net.littlelite.smartrest.dao

import net.littlelite.smartrest.model.Group
import net.littlelite.smartrest.model.Person
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonDAO : CrudRepository<Person, Long>
{
    fun findBySurname(lastName: String): List<Person>?
    fun findByEmail(email: String): Person?
    fun findByNameAndSurnameOrderBySurnameDesc(name: String, surname: String): List<Person>?
    fun findByGroupType(group: Group): List<Person>?
}