import com.google.gson.Gson
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.http.ContentType
import io.ktor.request.receive
import io.ktor.response.respondText
import io.ktor.routing.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
​
fun main() {
    val personRepository: PersonRepository = ImplPersonRepository()
    embeddedServer(Netty, 8080) {
        routing {
            install(ContentNegotiation) {
                gson {
                    setPrettyPrinting()
                }
            }
            ​
            // get all persons in memory
            get("/") {
                call.respondText(
                    Gson().toJson(
                        personRepository.getPersonList()
                    ), ContentType.Application.Json
                )
            }
            ​
            // get person with his id
            get("/id") {
                val idString = call.request.queryParameters["id"]
                val id = if (idString?.matches(Regex("-?[0-9]+")) == true) idString.toInt() else 0
                if (id == 0) call.respondText(
                    Gson().toJson(ResponseClass("bad request", "invalid id", null)),
                    ContentType.Application.Json
                ) else {
                    call.respondText(Gson().toJson(personRepository.getPersonById(id)), ContentType.Application.Json)
                }
            }
            ​
            // get person with emailId
            get("/email") {
                val emailId: String? = call.request.queryParameters["email"]
                call.respondText(
                    Gson().toJson(personRepository.getPersonByEmailId(emailId!!)),
                    ContentType.Application.Json
                )
            }
            ​
            // insert a person
            post("/") {
                val person = call.receive<PersonReq>()
                call.respondText(
                    Gson().toJson(personRepository.insertPerson(person)),
                    ContentType.Application.Json
                )
            }
            ​
            // update a person
            put("/") {
                val idString = call.request.queryParameters["id"]
                val id = if (idString?.matches(Regex("-?[0-9]+")) == true) idString.toInt() else 0
                if (id == 0) call.respondText(
                    Gson().toJson(ResponseClass("bad request", "invalid id", null)),
                    ContentType.Application.Json
                ) else {
                    val personEditReq = call.receive<PersonReq>()
                    call.respondText(
                        Gson().toJson(personRepository.updatePerson(id, personEditReq)),
                        ContentType.Application.Json
                    )
                }
            }
            ​
            // delete a person using id
            delete("/") {
                val idString = call.request.queryParameters["id"]
                val id = if (idString?.matches(Regex("-?[0-9]+")) == true) idString.toInt() else 0
                if (id == 0) call.respondText(
                    Gson().toJson(ResponseClass("bad request", "invalid id", null)),
                    ContentType.Application.Json
                ) else {
                    call.respondText(
                        Gson().toJson(personRepository.deletePerson(id)),
                        ContentType.Application.Json
                    )
                }
            }
        }
    }.start(true)
}
​
data class Person(
    val id: Int,
    val emailId: String,
    var firstName: String,
    var lastName: String,
    var city: String,
    var state: String,
    var country: String
) {
    fun toPersonRes(status: String, message: String) =
        ResponseClass(status, message, this)
}