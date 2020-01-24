package net.littlelite.smartrest

import net.littlelite.smartrest.dto.NewPersonDTO
import net.littlelite.smartrest.model.Group
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
    fun `Should have a working person service`()
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
        val newPerson = NewPersonDTO(
                "Ugo",
                "Tognazzi",
                "tognazziugo@supercazzola.com",
                Group.GUEST
        )
        val created = this.personService.createPerson(newPerson)
        assertThat(created).isNotNull
        assertThat(created!!.id).isGreaterThan(0)

        val retrieved = this.personService.getPerson(created.id)
        assertThat(retrieved).isNotNull
        assertThat(retrieved!!.email).isEqualTo("tognazziugo@supercazzola.com")

        this.personService.deletePerson(created.id)
        assertThat(this.personService.getAllPersons().size).isEqualTo(4)
    }
}
