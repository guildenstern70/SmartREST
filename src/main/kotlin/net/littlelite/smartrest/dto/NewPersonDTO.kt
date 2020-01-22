package net.littlelite.smartrest.dto

import net.littlelite.smartrest.model.Group
import net.littlelite.smartrest.model.Person

data class NewPersonDTO(
        val name: String,
        val surname: String,
        val email: String,
        val group: Group
)
{
    fun toPerson(): Person
    {
        return Person(
                this.name,
                this.surname,
                this.email,
                this.group
        )
    }
}