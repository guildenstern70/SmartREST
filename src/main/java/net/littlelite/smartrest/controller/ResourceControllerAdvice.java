/*
 * Copyright (c) Alessio Saltarin 2020.
 * This software is licensed under MIT License (see LICENSE)
 */

package net.littlelite.smartrest.controller;

import net.littlelite.smartrest.exceptions.ResourceAlreadyExists;
import net.littlelite.smartrest.exceptions.ResourceNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.mediatype.vnderrors.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@RequestMapping(produces = "application/vnd.error")
public class ResourceControllerAdvice
{
    @ResponseBody
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    VndErrors resourceNotFoundExceptionHandler(@NotNull ResourceNotFoundException ex)
    {
        return new VndErrors("Not Found Error", ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(ResourceAlreadyExists.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    VndErrors resourceAlreadyExistsExceptionHandler(ResourceAlreadyExists ex)
    {
        return new VndErrors("Already Exist Error", ex.getMessage());
    }

}


