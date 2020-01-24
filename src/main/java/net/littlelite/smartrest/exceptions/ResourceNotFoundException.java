/*
 * Copyright (c) Alessio Saltarin 2020.
 * This software is licensed under MIT License (see LICENSE)
 */

package net.littlelite.smartrest.exceptions;

public class ResourceNotFoundException extends RuntimeException
{
    public ResourceNotFoundException(long id)
    {
        super("Could not find resource with id: '" + id + "'");
    }

    public ResourceNotFoundException(String id)
    {
        super("Could not find resource with id: '" + id + "'");
    }

    public ResourceNotFoundException(Class<?> entityClass, String id)
    {
        super(entityClass.getSimpleName() + " #" + id + " not found");
    }

    public ResourceNotFoundException(Class<?> entityClass, int id)
    {
        super(entityClass.getSimpleName() + " #" + id + " not found");
    }
}

