package net.littlelite.smartrest.model

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Person(
        val name: String,
        val surname: String,
        val email: String
)
{
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Int = -1

        val creationDate = Date()

        constructor() : this("?", "?", "")

}
