/*
 * Project SmartREST
 * Copyright (c) Alessio Saltarin 2022.
 * This software is licensed under MIT License (see LICENSE)
 */

package net.littlelite.smartrest.dto

import net.littlelite.smartrest.model.Person
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

data class PersonDTO(
    val id: Long,
    val name: String,
    val surname: String,
    val email: String,
    val creationDate: String,
    val group: String,
    val phones: List<PhoneDTO>
)
{
    /**
     * The companion method can be called as
     * PersonDTO.create(person)
     */
    companion object
    {
        @JvmStatic
        fun fromPerson(person: Person?): PersonDTO?
        {
            if (person == null)
                return null

            val creation = this.dateToString(person.creationDate)
            val group = person.groupType.name

            val phones = person.getPhones().map {
                PhoneDTO(it.number, it.provider)
            }

            return PersonDTO(
                person.id,
                person.name,
                person.surname,
                person.email,
                creation,
                group,
                phones
            )
        }

        private fun dateToString(date: Date): String
        {
            val dateFormat: DateFormat = SimpleDateFormat("dd-mm-yy hh:mm:ss")
            return dateFormat.format(date)
        }
    }
}