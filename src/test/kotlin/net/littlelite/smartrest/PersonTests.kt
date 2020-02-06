/*
 * Copyright (c) Alessio Saltarin 2020.
 * This software is licensed under MIT License (see LICENSE)
 */

package net.littlelite.smartrest

import net.littlelite.smartrest.dto.NewPersonDTO
import net.littlelite.smartrest.dto.PhoneDTO
import net.littlelite.smartrest.model.Group
import net.littlelite.smartrest.model.Provider
import net.littlelite.smartrest.service.PersonService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest
internal class PersonTests
{
    @Autowired
    private lateinit var personService: PersonService

    @Test
    fun `Should invoke a working person service`()
    {
        assertThat(this.personService).isNotNull
    }

    @Test
    fun `Should initially have 4 persons`()
    {
        val persons = this.personService.getAllPersons()
        assertThat(persons.size).isEqualTo(4)
    }

    @Test
    fun `Should create and delete a new person`()
    {
        val phone1 = PhoneDTO("+39-3494838990", Provider.TIM)
        val phone2 = PhoneDTO("+49-3339292991", Provider.VODAFONE)
        val newPerson = NewPersonDTO(
                "Ugo",
                "Tognazzi",
                "tognazziugo@supercazzola.com",
                Group.GUEST
        )
        val created = this.personService.createPerson(newPerson,
                listOf(phone1, phone2))
        assertThat(created).isNotNull
        assertThat(created!!.id).isGreaterThan(0)
        assertThat(created.phones.size).isEqualTo(2)

        val retrieved = this.personService.getPerson(created.id)
        assertThat(retrieved).isNotNull
        assertThat(retrieved!!.email).isEqualTo("tognazziugo@supercazzola.com")
        assertThat(retrieved.phones[0].number).isEqualTo("+39-3494838990")

        this.personService.deletePerson(created.id)
        assertThat(this.personService.getAllPersons().size).isEqualTo(4)
    }

    @Test
    fun `Should get a person by name and surname`()
    {
        val alessioPersons = this.personService.getPersonsByFullName("Alessio", "Saltarin")
        assertThat(alessioPersons).isNotNull
        assertThat(alessioPersons!!.size).isEqualTo(1)
        val alessio = alessioPersons[0]
        assertThat(alessio.name).isEqualTo("Alessio")
        assertThat(alessio.surname).isEqualTo("Saltarin")
    }
}
