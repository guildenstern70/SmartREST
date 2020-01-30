/*
 * Copyright (c) Alessio Saltarin 2020.
 * This software is licensed under MIT License (see LICENSE)
 */

package net.littlelite.smartrest.service

import net.littlelite.smartrest.dao.PersonDAO
import net.littlelite.smartrest.dto.NewPersonDTO
import net.littlelite.smartrest.dto.PersonDTO
import net.littlelite.smartrest.model.Person
import org.springframework.stereotype.Service

@Service
class PersonService(
        val personDAO: PersonDAO
)
{
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
        val person = this.personDAO.findById(id)
        if (person.isEmpty) return null
        return PersonDTO.create(person.get())
    }

    fun createPerson(person: NewPersonDTO): PersonDTO?
    {
        val existingPerson = this.personDAO.findByEmail(person.email)
        return if (existingPerson != null)
        {
            null // this person already exists
        }
        else
        {
            PersonDTO.create(this.personDAO.save(person.toPerson()))
        }
    }

    fun deletePerson(id: Long)
    {
        this.personDAO.deleteById(id)
    }

    fun editPerson(id: Long, person: NewPersonDTO): PersonDTO?
    {
        val optExistingPerson = this.personDAO.findById(id)
        if (optExistingPerson.isEmpty)
            return null
        val existingPerson = optExistingPerson.get()
        existingPerson.changeTo(person)
        return PersonDTO.create(this.personDAO.save(existingPerson))
    }
}