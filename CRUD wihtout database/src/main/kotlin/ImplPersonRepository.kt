import arrow.core.extensions.list.foldable.exists
import arrow.core.toOption
import com.google.gson.Gson
import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.response.respondText
​
class ImplPersonRepository : PersonRepository {
    private val listOfPerson = mutableListOf(
        Person(1, "vishnu@vayana.com", "Vishnu", "Shelke", "pune", "Maharashtra", "India"),
        Person(2, "supriya@vayana.com", "Supriya", "Kengar", "mumbai", "Maharashtra", "India")
    )
    ​
    override fun getPersonList(): ResponseClass =
        ResponseClass(
            "success",
            "successfully fetched all person",
            listOfPerson
        )
    ​
    override fun getPersonById(id: Int): ResponseClass {
        val person = listOfPerson.find { it.id == id }.toOption()
        return person.fold({
            ResponseClass("bad request", "person with this id does not exist", null)
            ​
        }, {
            it.toPersonRes("success", "person fetched successfully with id")
        })
    }
    ​
    override fun getPersonByEmailId(emailId: String): ResponseClass {
        val person = listOfPerson.find { it.emailId == emailId }.toOption()
        return person.fold({
            ResponseClass("bad request", "person with this emailId does not exist", null)
            ​
        }, {
            it.toPersonRes("success", "person fetched successfully with emailId")
        })
    }
    ​
    override fun insertPerson(personReq: PersonReq): ResponseClass =
        if (listOfPerson.exists { it.emailId == personReq.emailId })
            ResponseClass(
                "bad request",
                "person with emailId already exist", listOfPerson.find { it.emailId == personReq.emailId }
            )
        else {
            val id = listOfPerson.last().id + 1
            listOfPerson.add(personReq.toPersonObj(id))
            ResponseClass("success", "person added successfully", personReq.toPersonObj(id))
        }
    ​
    ​
    override fun updatePerson(id: Int, personEditReq: PersonReq): ResponseClass {
        var found = false
        listOfPerson.map {
            if (it.id == id && it.emailId == personEditReq.emailId) {
                it.city = personEditReq.city
                it.firstName = personEditReq.firstName
                it.lastName = personEditReq.lastName
                it.country = personEditReq.country
                it.state = personEditReq.state
                found = true
            }
        }
        return if (!found)
            ResponseClass(
                "bad request",
                "id or emailId error", null
            )
        else
            ResponseClass(
                "success",
                "successfully updated person",
                listOfPerson.find { it.id == id })
    }
    ​
    override fun deletePerson(id: Int): ResponseClass =
        if (!listOfPerson.exists { it.id == id })
            ResponseClass(
                "bad request",
                "person with this id does not exist", null
            )
        else {
            val personToDelete = listOfPerson.find { it.id == id }
            listOfPerson.remove(personToDelete)
            ResponseClass(
                "success",
                "deleted person successfully",
                personToDelete
            )
        }
}