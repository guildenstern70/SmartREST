/*
 * Copyright (c) Alessio Saltarin 2020.
 * This software is licensed under MIT License (see LICENSE)
 */

package net.littlelite.smartrest.controller.rest;

import net.littlelite.smartrest.dto.NewPersonDTO;
import net.littlelite.smartrest.dto.PersonDTO;
import net.littlelite.smartrest.exceptions.ResourceAlreadyExists;
import net.littlelite.smartrest.exceptions.ResourceNotFoundException;
import net.littlelite.smartrest.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value = "/api/v1/persons")
public class PersonController
{
    private final Logger logger = LoggerFactory.getLogger(PersonController.class);

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService)
    {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<List<PersonDTO>> getAllPersons()
    {
        logger.info("GET all persons");
        return new ResponseEntity<>(this.personService.getAllPersons(), OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePersonById(@PathVariable("id") long id)
    {
        logger.info("DELETE person #" + id);
        this.personService.deletePerson(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("findby/name/{name}/surname/{surname}")
    public ResponseEntity<?> getPersonByNameAndSurname(@PathVariable("name") String name,
                                                       @PathVariable("surname") String surname)
    {
        logger.info("GET person by name=" + name + " and surname=" +surname);
        var persons = this.personService.getPersonsByFullName(name, surname);

        if (persons == null)
            throw new ResourceNotFoundException(name + " " + surname);

        return new ResponseEntity<>(persons.stream().map(PersonDTO::create), OK);
    }

    @GetMapping("findbynameandsurname")
    public ResponseEntity<?> getPersonByNameAndSurnameQueries(@RequestParam("name") String name,
                                                       @RequestParam("surname") String surname)
    {
        logger.info("GET person by queries name=" + name + " and surname=" +surname);
        var persons = this.personService.getPersonsByFullName(name, surname);

        if (persons == null)
            throw new ResourceNotFoundException(name + " " + surname);

        return new ResponseEntity<>(persons.stream().map(PersonDTO::create), OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPersonById(@PathVariable("id") long id)
    {
        logger.info("GET person #" + id);
        var person = this.personService.getPerson(id);

        if (person == null)
            throw new ResourceNotFoundException(id);

        return new ResponseEntity<>(person, OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editPersonById(@PathVariable("id") long id,
                                            @Valid @RequestBody NewPersonDTO person)
    {
        this.logger.info("Received PUT REQUEST for Person #" + id);

        var modifiedPerson = this.personService.editPerson(id, person);
        if (modifiedPerson == null)
        {
            throw new ResourceNotFoundException(id);
        }

        return new ResponseEntity<>(person, OK);
    }

    @PostMapping
    public ResponseEntity<?> createPerson(@Valid @RequestBody NewPersonDTO person,
                                         UriComponentsBuilder uriComponentsBuilder)
    {
        this.logger.info("Received POST REQUEST to create a new person");

        var createdPerson = this.personService.createPerson(person);
        if (createdPerson == null)
        {
            this.logger.warn("A person with email " + person.getEmail() + " already exists.");
            throw new ResourceAlreadyExists(person.getEmail());
        }
        else
        {
            final HttpHeaders headers = new HttpHeaders();
            headers.setLocation(uriComponentsBuilder.path("/api/v1/persons/{id}")
                    .buildAndExpand(
                            createdPerson.getId()
                    )
                    .toUri()
            );
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        }
    }

}
