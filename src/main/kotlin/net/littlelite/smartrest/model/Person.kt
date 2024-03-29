/*
 * Project SmartREST
 * Copyright (c) Alessio Saltarin 2022-23
 * This software is licensed under MIT License (see LICENSE)
 */

package net.littlelite.smartrest.model

import net.littlelite.smartrest.dto.NewPersonDTO
import java.util.*
import jakarta.persistence.*

@Entity
data class Person(
    var name: String,

    var surname: String,

    var email: String,

    @Enumerated(EnumType.STRING)
    var groupType: Group
)
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = -1

    var creationDate = Date()

    @OneToMany(
        cascade = [(CascadeType.ALL)],
        fetch = FetchType.EAGER,
        mappedBy = "person"
    )
    private var personPhones = mutableListOf<Phone>()

    fun getPhones() = this.personPhones

    fun setPhones(providedPhones: List<Phone>)
    {
        providedPhones.forEach { this.addPhone(it) }
    }

    fun addPhone(phone: Phone)
    {
        phone.person = this
        this.personPhones.add(phone)
    }

    fun changeTo(newFields: NewPersonDTO)
    {
        this.creationDate = Date()
        this.email = newFields.email
        this.groupType = newFields.group
        this.name = newFields.name
        this.surname = newFields.surname
    }

}
