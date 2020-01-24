/*
 * Copyright (c) Alessio Saltarin 2020.
 * This software is licensed under MIT License (see LICENSE)
 */

package net.littlelite.smartrest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SmartRestApplication implements CommandLineRunner
{
	private final Logger logger = LoggerFactory.getLogger(SmartRestApplication.class);

	private void hello()
	{
		logger.info("*****************************************************************");
		logger.info("  SmartREST v.0.1.0");
		logger.info("  Running on http://localhost:8080 (JVM " + System.getProperty("java.version") + ")");
		logger.info("*****************************************************************");
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
