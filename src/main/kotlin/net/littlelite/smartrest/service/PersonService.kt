package net.littlelite.smartrest.service

import net.littlelite.smartrest.dao.PersonDAO
import net.littlelite.smartrest.dto.PersonDTO
import org.springframework.stereotype.Service

@Service
class PersonService(
        val personDAO: PersonDAO
)
{
    fun getAllPersons(): List<PersonDTO> =
            this.personDAO.findAll().toList().mapNotNull {
                PersonDTO.create(it)
            }

    fun getPerson(id: Long): PersonDTO?
    {
        val person = this.personDAO.findById(id)
        if (person.isEmpty) return null
        return PersonDTO.create(person.get())
    }
}