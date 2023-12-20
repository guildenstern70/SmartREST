/*
 * Project SmartREST
 * Copyright (c) Alessio Saltarin 2022-23
 * This software is licensed under MIT License (see LICENSE)
 */


package net.littlelite.smartrest.exceptions

class ResourceAlreadyExists : RuntimeException
{
    constructor(id: String) : super("Resource with ID: '$id' already exists.")
    constructor(id: Int) : super("Resource with ID: '$id' already exists.")
}

