
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import java.io.File

/*data class Person(val name: String, val age: Int, val messages: List<String>) {
}
*/

@JsonInclude(JsonInclude.Include.NON_EMPTY)//doesn't includes empty feilds
data class Person(
    @JsonProperty("name")
    val name: String,
    @JsonProperty("age")
    val age: Int,
    @JsonProperty("msg")
    val messages: List<String>
)

fun main(args: Array<String>) {

    val json = """[
        {"name": "supriya", "age": "22", "msg" : ["hello","i m coming"]},
        {"name": "shweta", "age": "", "msg" : ["hello","hiii"]},
        {"name": "monika", "age": "24", "msg" : ["heya","hiii"]}
        ]"""

  //val mapper = jacksonObjectMapper()
    val mapper = ObjectMapper().registerKotlinModule()


    println(" JSON to Kotlin Object ")

    println(" read String")
    var person: List<Person> = mapper.readValue(json)
    println(person)


    println("read from File")
    person = mapper.readValue(File("person.json"))
    println(person)

    //====================================================================//

    println(" Kotlin Object to JSON ")


    val personList : List<Person> = listOf(
        Person("", 22, listOf("heya", "hiiiiii") ),
        Person("Shweta", 23, listOf("hiiiiii", "hhhhhaaaaaaaaa") ),
        Person("Monika", 0,listOf("ttttooooo", "djjfjdjfjhd") )
    );




    println("JSON  String")
    var jsonStr = mapper.writeValueAsString(personList)
    println(jsonStr)

    println("Formatted String using pretty printer")
    jsonStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(personList)
    println(jsonStr)

   println(" manually check file for result")
    mapper.writerWithDefaultPrettyPrinter().writeValue(File("newPerson.json"), personList)

}
