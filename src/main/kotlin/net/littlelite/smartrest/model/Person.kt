package net.littlelite.smartrest.model

import java.util.*
import javax.persistence.*

@Entity
data class Person(
        val name: String,

        val surname: String,

        val email: String,

        @Enumerated(EnumType.STRING)
        val groupType: Group
)
{
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = -1

        val creationDate = Date()

        constructor() : this("?", "?", "", Group.UNKNOWN)

}
