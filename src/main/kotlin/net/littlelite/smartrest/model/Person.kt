/*
 * Project SmartREST
 * Copyright (c) Alessio Saltarin 2021.
 * This software is licensed under MIT License (see LICENSE)
 */

package net.littlelite.smartrest.model

import net.littlelite.smartrest.dto.NewPersonDTO
import java.util.*
import javax.persistence.*

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
    var phones = mutableListOf<Phone>()

    fun changeTo(newFields: NewPersonDTO)
    {
        this.creationDate = Date()
        this.email = newFields.email
        this.groupType = newFields.group
        this.name = newFields.name
        this.surname = newFields.surname
    }

}
