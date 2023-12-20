/*
* Project SmartREST
* Copyright (c) Alessio Saltarin 2022-23
* This software is licensed under MIT License (see LICENSE)
*/

package net.littlelite.smartrest.controller.rest

import net.littlelite.smartrest.controller.rest.PersonController
import net.littlelite.smartrest.dto.NewPersonDTO
import net.littlelite.smartrest.dto.PersonDTO
import net.littlelite.smartrest.exceptions.ResourceAlreadyExists
import net.littlelite.smartrest.exceptions.ResourceNotFound
import net.littlelite.smartrest.model.Person
import net.littlelite.smartrest.service.PersonService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder


@RestController
@RequestMapping(value = ["/api/v1/persons"])
class PersonController @Autowired constructor(private val personService: PersonService)
{
    private val logger = LoggerFactory.getLogger(PersonController::class.java)

    @get:GetMapping
    val allPersons: ResponseEntity<List<PersonDTO>>
        get()
        {
            logger.info("GET all persons")
            return ResponseEntity(personService.getAllPersons(), HttpStatus.OK)
        }

    @DeleteMapping("/{id}")
    fun deletePersonById(@PathVariable("id") id: Long): ResponseEntity<*>
    {
        logger.info("DELETE person #$id")
        personService.deletePerson(id)
        return ResponseEntity<Any>(HttpStatus.NO_CONTENT)
    }

    @GetMapping("findby/name/{name}/surname/{surname}")
    fun getPersonByNameAndSurname(
        @PathVariable("name") name: String,
        @PathVariable("surname") surname: String
    ): ResponseEntity<*>
    {
        logger.info("GET person by name=$name and surname=$surname")
        val persons = personService.getPersonsByFullName(name, surname) ?: throw ResourceNotFound("$name $surname")
        return ResponseEntity(persons.stream().map { obj: Person? -> PersonDTO.fromPerson(obj) }, HttpStatus.OK)
    }

    @GetMapping("findbynameandsurname")
    fun getPersonByNameAndSurnameQueries(
        @RequestParam("name") name: String,
        @RequestParam("surname") surname: String
    ): ResponseEntity<*>
    {
        logger.info("GET person by queries name=$name and surname=$surname")
        val persons = personService.getPersonsByFullName(name, surname) ?: throw ResourceNotFound("$name $surname")
        return ResponseEntity(persons.stream().map { obj: Person? -> PersonDTO.fromPerson(obj) }, HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun getPersonById(@PathVariable("id") id: Long): ResponseEntity<*>
    {
        logger.info("GET person #$id")
        val person = personService.getPerson(id) ?: throw ResourceNotFound(id)
        return ResponseEntity(person, HttpStatus.OK)
    }

    @PutMapping("/{id}")
    fun editPersonById(
        @PathVariable("id") id: Long,
        @RequestBody person: NewPersonDTO?
    ): ResponseEntity<*>
    {
        logger.info("Received PUT REQUEST for Person #$id")
        val personDto = personService.editPerson(id, person!!)
            ?: throw ResourceNotFound(id)
        return ResponseEntity(personDto, HttpStatus.OK)
    }

    @PostMapping
    fun createPerson(
        @RequestBody person: NewPersonDTO,
        uriComponentsBuilder: UriComponentsBuilder
    ): ResponseEntity<*>
    {
        logger.info("Received POST REQUEST to create a new person")
        val createdPerson = personService.createPerson(person, null)
        return if (createdPerson == null)
        {
            logger.warn("A person with email " + person.email + " already exists.")
            throw ResourceAlreadyExists(person.email)
        } else
        {
            val headers = HttpHeaders()
            headers.location = uriComponentsBuilder.path("/api/v1/persons/{id}")
                .buildAndExpand(
                    createdPerson.id
                )
                .toUri()
            ResponseEntity<Any>(headers, HttpStatus.CREATED)
        }
    }
}
