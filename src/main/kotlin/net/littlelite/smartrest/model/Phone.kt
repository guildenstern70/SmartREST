/*
 * Project SmartREST
 * Copyright (c) Alessio Saltarin 2022-23
 * This software is licensed under MIT License (see LICENSE)
 */

package net.littlelite.smartrest.model

import jakarta.persistence.*


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
