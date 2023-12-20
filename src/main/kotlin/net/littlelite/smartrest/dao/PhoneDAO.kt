/*
 * Project SmartREST
 * Copyright (c) Alessio Saltarin 2022-23
 * This software is licensed under MIT License (see LICENSE)
 */

package net.littlelite.smartrest.dao

import net.littlelite.smartrest.model.Phone
import net.littlelite.smartrest.model.Provider
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PhoneDAO : CrudRepository<Phone, Long>
{
    fun findByProvider(provider: Provider): List<Phone>?
}
