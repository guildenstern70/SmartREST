/*
 * Project SmartREST
 * Copyright (c) Alessio Saltarin 2022.
 * This software is licensed under MIT License (see LICENSE)
 */

package net.littlelite.smartrest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class SmartRestApplication implements CommandLineRunner
{
    private final Logger logger = LoggerFactory.getLogger(SmartRestApplication.class);
    public static final String VERSION = "0.3.4";

    private Environment environment;

    @Autowired
    public SmartRestApplication(Environment environment)
    {
        this.environment = environment;
    }

    private void hello()
    {
        String runningUrl = "http://localhost:" + this.environment.getProperty("local.server.port");
        logger.info("*******************************************");
        logger.info("  SmartREST v." + VERSION);
        logger.info("  Running on " + runningUrl + " (JVM " + System.getProperty("java.version") + ")");
        logger.info("*******************************************");
    }

    public static void main(String[] args)
    {
        SpringApplication.run(SmartRestApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception
    {
        this.hello();
    }
}
