/*
 * Copyright (c) Alessio Saltarin 2020.
 * This software is licensed under MIT License (see LICENSE)
 */

package net.littlelite.smartrest.model

import javax.persistence.*

@Entity
data class Phone(
        val number: String,
        val provider: Provider,
        @ManyToOne(fetch = FetchType.EAGER, optional = false)
        @JoinColumn(name = "person_id")
        val person: Person? = null
)
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = -1

    constructor() : this("?", Provider.UNKNOWN)
}
