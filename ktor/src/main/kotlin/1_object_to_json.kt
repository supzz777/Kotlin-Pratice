package danny_sir_learnings

import java.io.File
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.Month

fun main() {
    val mapper = getObjectMapper()

    // printing to the file after converting object into json using ObjectMapper
   val person = Person("supriyakengar@vayana.com", "supriya", "kengar",
        "mumbai", "Maharashtra", "India", LocalDate.now() ).validateData(FaultType.Validation)



    person.fold({ println(it.message) },{
        mapper.writerWithDefaultPrettyPrinter().writeValue(File("files/personWriteData.json"),it)
    })


    // Converting an object to string using ObjectMapper
    person.fold({ println(it.message)},{
        val personString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(it)
        println(personString)
    })
}

