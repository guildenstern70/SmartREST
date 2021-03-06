/*
 * Project SmartREST
 * Copyright (c) Alessio Saltarin 2021.
 * This software is licensed under MIT License (see LICENSE)
 */

package net.littlelite.smartrest.service;

import net.littlelite.smartrest.dao.PersonDAO;
import net.littlelite.smartrest.model.Group;
import net.littlelite.smartrest.model.Person;
import net.littlelite.smartrest.model.Phone;
import net.littlelite.smartrest.model.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DbInitializer implements ApplicationRunner
{
    protected static final Logger logger = LoggerFactory.getLogger(DbInitializer.class);

    private PersonDAO personDAO;

    @Autowired
    public DbInitializer(PersonDAO personDAO)
    {
        this.personDAO = personDAO;
    }

    @Override
    public void run(ApplicationArguments args)
    {
        logger.info("Populating Database");

        List<Phone> phones = Arrays.asList(
                new Phone("348-39020292", Provider.TIM, null),
                new Phone("333-32232211", Provider.VODAFONE, null));

        Person[] persons = new Person[]{
                new Person("Alessio", "Saltarin",
                        "alessiosaltarin@gmail.com", Group.ADMINISTRATOR),
                new Person("Laura", "Renzi",
                        "laurarenzi@gmail.com", Group.USER),
                new Person("Elena", "Santandrea",
                        "elenasan@gmail.com", Group.USER),
                new Person("Filippo", "Giusti",
                        "filippogiuisti@outlook.com", Group.GUEST)
        };

        Arrays.stream(persons)
                .forEach(p -> {
                    p.setPhones(phones);
                    logger.info("Saving " + p);
                    this.personDAO.save(p);
                });

    }
}
