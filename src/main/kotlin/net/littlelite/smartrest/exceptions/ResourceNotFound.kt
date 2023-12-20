/*
 * Project SmartREST
 * Copyright (c) Alessio Saltarin 2022-23
 * This software is licensed under MIT License (see LICENSE)
 */


package net.littlelite.smartrest.exceptions

class ResourceNotFound : RuntimeException
{
    constructor(id: Long) : super("Could not find resource with id: '$id'")
    constructor(id: String) : super("Could not find resource with id: '$id'")
    constructor(entityClass: Class<*>, id: String) : super(entityClass.simpleName + " #" + id + " not found")
    constructor(entityClass: Class<*>, id: Int) : super(entityClass.simpleName + " #" + id + " not found")
}
