/*
 * Project SmartREST
 * Copyright (c) Alessio Saltarin 2021.
 * This software is licensed under MIT License (see LICENSE)
 */

package net.littlelite.smartrest.exceptions;

public class ResourceAlreadyExists extends RuntimeException
{
    public ResourceAlreadyExists(String id)
    {
        super("Resource with ID: '" + id + "' already exists.");
    }

    public ResourceAlreadyExists(int id)
    {
        super("Resource with ID: '" + id + "' already exists.");
    }
}


