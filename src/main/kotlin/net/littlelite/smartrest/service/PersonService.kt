/*
 * Project SmartREST
 * Copyright (c) Alessio Saltarin 2021.
 * This software is licensed under MIT License (see LICENSE)
 */

package net.littlelite.smartrest.service

import net.littlelite.smartrest.dao.PersonDAO
import net.littlelite.smartrest.dao.PhoneDAO
import net.littlelite.smartrest.dto.NewPersonDTO
import net.littlelite.smartrest.dto.PersonDTO
import net.littlelite.smartrest.dto.PhoneDTO
import net.littlelite.smartrest.model.Person
import net.littlelite.smartrest.model.Phone
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*

@Service
class PersonService(
    val personDAO: PersonDAO,
    val phoneDAO: PhoneDAO
)
{
    private val logger = LoggerFactory.getLogger(PersonService::class.java)

    fun getAllPersons(): List<PersonDTO> =
        this.personDAO.findAll().toList().mapNotNull {
            PersonDTO.create(it)
        }

    fun getPersonsByFullName(name: String, surname: String): List<Person>?
    {
        return this.personDAO.findByNameAndSurnameOrderBySurnameDesc(name, surname);
    }

    fun getPerson(id: Long): PersonDTO?
    {
        val person: Optional<Person> = this.personDAO.findById(id)
        if (person.isPresent) return PersonDTO.create(person.get())
        return null
    }

    fun createPerson(person: NewPersonDTO, phones: List<PhoneDTO>? = null): PersonDTO?
    {
        val existingPerson = this.personDAO.findByEmail(person.email)
        return if (existingPerson != null)
        {
            null // this person already exists
        } else
        {
            var newPerson = person.toPerson()
            newPerson = this.personDAO.save(newPerson)
            phones?.map {
                it.toPhone(newPerson)
            }?.forEach {
                    this.phoneDAO.save(it)
                    newPerson.phones.add(it)
                }
            newPerson = this.personDAO.save(newPerson)
            PersonDTO.create(newPerson)
        }
    }

    fun deletePerson(id: Long)
    {
        this.personDAO.deleteById(id)
    }

    fun editPerson(id: Long, person: NewPersonDTO): PersonDTO?
    {
        val optExistingPerson = this.personDAO.findById(id)
        if (optExistingPerson.isPresent)
        {
            val existingPerson = optExistingPerson.get()
            existingPerson.changeTo(person)
            return PersonDTO.create(this.personDAO.save(existingPerson))
        }
        return null
    }
}