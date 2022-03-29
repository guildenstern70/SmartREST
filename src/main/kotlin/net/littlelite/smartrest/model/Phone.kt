/*
 * Project SmartREST
 * Copyright (c) Alessio Saltarin 2022.
 * This software is licensed under MIT License (see LICENSE)
 */

package net.littlelite.smartrest.model

import javax.persistence.*

@Entity
data class Phone(
    val number: String,
    val provider: Provider,
    @ManyToOne
    var person: Person? = null
)
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = -1
}
