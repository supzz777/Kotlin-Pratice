import arrow.core.Either
import java.lang.Exception
​
interface PersonRepository {
    ​
    fun getPersonList(): ResponseClass
    ​
    fun getPersonById(id: Int): ResponseClass
    ​
    fun getPersonByEmailId(emailId: String): ResponseClass
    ​
    fun insertPerson(personReq: PersonReq): ResponseClass
    ​
    fun updatePerson(id: Int, personReq: PersonReq): ResponseClass
    ​
    fun deletePerson(id: Int): ResponseClass
}