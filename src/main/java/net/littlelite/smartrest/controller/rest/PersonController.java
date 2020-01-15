package net.littlelite.smartrest.controller.rest;

import net.littlelite.smartrest.dto.PersonDTO;
import net.littlelite.smartrest.exceptions.ResourceNotFoundException;
import net.littlelite.smartrest.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/{id}")
    public ResponseEntity<?> getPersonById(@PathVariable("id") long id)
    {
        logger.info("GET person #" + id);
        var person = this.personService.getPerson(id);

        if (person == null)
            throw new ResourceNotFoundException(id);

        return new ResponseEntity<>(person, OK);
    }

}
