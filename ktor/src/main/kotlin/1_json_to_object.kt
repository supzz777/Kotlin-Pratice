package danny_sir_learnings

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import java.io.File
import java.time.LocalDate

@JsonInclude(JsonInclude.Include.NON_NULL) // exclude null fields from JSON
data class Person(
    @JsonAlias("EmailId") // uses different attribute names, but still keeps a canonical representation
    val emailId: String,
    @JsonProperty("first_name", access = JsonProperty.Access.AUTO) // we can give access while reading json
    val firstName: String,
    @JsonProperty("last_name")
    val lastName: String,
    val city: String,
    val state: String,
    val country: String,
    // pass date as a parameter
    @JsonFormat( pattern = "dd/MM/yyyy",shape = JsonFormat.Shape.STRING)
    @JsonSerialize(using = LocalDateSerializer::class)
    @JsonDeserialize(using = LocalDateDeserializer::class)
    @field:DateAfter(message = "invalid given date", date="05/12/2019")
    //custom date validator
    val createdTime: LocalDate
)

fun getObjectMapper(): ObjectMapper =
    jacksonObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)


fun parseAndReturnJsonNode(str: String): JsonNode = ObjectMapper().readTree(str)

fun main() {
   val mapper = getObjectMapper()
   // val mapper = ObjectMapper().registerKotlinModule()

    // reading and getting Json Node Using ObjectMapper
    val jsonString =
        """{"emailId":"supriyakengar@vayana.com","first_name":"supriya","last_name":"kengar",
            |"city":"mumbai","state":"Maharashtra","country":"India","createdTime":"16/05/2020"}""".trimMargin()
    val jsonNode = parseAndReturnJsonNode(jsonString)
    println("########### Json Node #############")
    println("emailId is ${jsonNode.findValue("emailId")} \n\n")

    // Converting to Person Object using mapper
    val person = mapper.readValue(jsonString, Person::class.java)
    println("########### Converting Json to Person Object #############")
    println("$person \n\n")

    // Converting into an Object by reading a file
    val file = File("files/person.json")
    val personFromFile = mapper.readValue(file, Person::class.java)
    println("########### Converting Json from file to Person Object #############")
    print(personFromFile)
}
