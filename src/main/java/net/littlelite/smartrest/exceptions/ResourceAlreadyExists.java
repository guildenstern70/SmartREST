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


