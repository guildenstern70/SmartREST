package net.littlelite.smartrest.service;

import net.littlelite.smartrest.dao.PersonDAO;
import net.littlelite.smartrest.model.Group;
import net.littlelite.smartrest.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

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
    public void run(ApplicationArguments args) throws Exception
    {
        logger.info("Populating Database");

        Person[] persons = new Person[]{
                new Person("Alessio", "Saltarin",
                        "alessiosaltarin@gmail.com"),
                new Person("Laura", "Renzi",
                        "laurarenzi@gmail.com"),
                new Person("Elena", "Santandrea",
                        "elenasan@gmail.com"),
                new Person("Filippo", "Giusti",
                        "filippogiuisti@outlook.com")
        };

        for (var p : persons)
        {
            logger.info("Looking for " + p.getEmail());
            if (this.personDAO.findByEmail(p.getEmail()) == null)
            {
                logger.info("Saving " + p);
                this.personDAO.save(p);
            }
            else
            {
                logger.info("... it exists with ID=" + p.getId());
            }
        }
    }
}
