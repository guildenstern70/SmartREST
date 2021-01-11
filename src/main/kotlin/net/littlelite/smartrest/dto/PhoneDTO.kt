/*
 * Project SmartREST
 * Copyright (c) Alessio Saltarin 2021.
 * This software is licensed under MIT License (see LICENSE)
 */

package net.littlelite.smartrest.dto

import net.littlelite.smartrest.model.Provider

data class PhoneDTO(
        val number: String,
        val provider: Provider
)
