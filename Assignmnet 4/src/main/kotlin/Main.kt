
import arrow.core.*
import arrow.core.extensions.fx
import com.google.gson.Gson
import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction


object Contact : /*IntId*/Table("contacts"){
    val id : Column<Int> = integer("id").autoIncrement()
    val firstName: Column<String> = varchar("firstName", length = 50)
    val lastName: Column<String> = varchar("lastName", length = 50)
    val adress : Column<String> = varchar("adress", length = 100)
    val email : Column<String> = varchar("email",length=50)
    val phoneNo : Column<String> = varchar("phoneNumber", length=50)

}


  data class Contacts( val id: Int , val firstName: String,val lastName: String,val adress: String,
                    val email: String, val phoneNo:String)


object Contactzzzzzzzzz : /*IntId*/IntIdTable("contactzzzzzzzzzzzz"){
    val firstName: Column<String> = varchar("firstName", length = 50)
    val lastName: Column<String> = varchar("lastName", length = 50)
    val adress : Column<String> = varchar("adress", length = 100)
    val email : Column<String> = varchar("email",length=50)
    val phoneNo : Column<String> = varchar("phoneNumber",length = 50)

}



// funtion to connect our application with the mysql database.
fun initDB() {
    val url = "jdbc:mysql://localhost:3306/ktor"
    val driver = "com.mysql.cj.jdbc.Driver"
    val username = "root"
    val password = "admin@123"
    val db = Database.connect(url,driver,username,password)
}


//function to create the table automatically inside the mysql database.
fun createTableAutomatically():String {

    transaction {
        SchemaUtils.create(Contactzzzzzzzzz)

    }

    return "SUCESS"
}


//function to display the data of the table inside the mysql database.
//for this we are using resultRow which is the inbuilt function in DSL
fun ResultRow.toContacts() =
    Contacts(
        this[Contact.id],
        this[Contact.firstName],
        this[Contact.lastName],
        this[Contact.adress],
        this[Contact.email],
        this[Contact.phoneNo]


    )


fun getContacts(): Either<Exception, Option<Contacts>> =

    Either.fx{
        transaction {

            val result = Contact.select{
                Contact.id eq 2
            }.singleOrNone() // even if we have multiple entries in our database it
                            //  should return only single data set so we are throwing
                             // the exception. In case of multiple data set it will return nothing.

            result.fold({ None } , {
                 val contactz = it.toContacts()
                contactz.toOption()

            })
        }    //By using fold we decide what to return if exception is happened and what to return
              // in case of no exception

    }


//fuction to show all the present data inside the database.
fun showAllData():String {
     var json: String = ""
    transaction {
        var result = Contact.selectAll()

        val contacts = ArrayList<Contacts>()
        for (i in result) {

            contacts.add(Contacts( id =i[Contact.id],firstName = i[Contact.firstName],
                lastName =  i[Contact.lastName], email = i[Contact.email] , adress = i[Contact.adress] ,
                phoneNo = i[Contact.phoneNo]) )
        }


        json = Gson().toJson(contacts);
    }


    println(json)
    return json
}





//function to insert the new contact into our table in database.
fun insertContactDetails():String { var json:String =""

    transaction {
        val insert =  Contact.insert {

            it[firstName] ="Pooja"
            it[lastName] = "Waghmare"
            it[adress] = "Trombay Mumbai-84"
            it[email] = "cokdya@gmail.com"
            it[phoneNo]= "9982346430"

        }
    }


    return "SUCESS"
}


fun main(args: Array<String>) //Application entry point
{
    initDB()
    //implementing netty server
    val server = embeddedServer(Netty, 8080) {
        //Install the routing fetaure and starts configuring it
        routing {
            //define a route which will handle specific path
            get("/") {

                call.respondText("Hello, world!", ContentType.Text.Html)
            }
            get("/show"){
                val  contact = getContacts()
                call.respondText(contact.toString(), ContentType.Text.Html)
            }
            get("/showAll"){
                call.respondText(showAllData(), ContentType.Text.Html)
            }
            post("/insertContact"){
                call.respondText(insertContactDetails(), ContentType.Text.Plain)

            }
            get("/createTableAutomatically"){
                call.respondText(createTableAutomatically(), ContentType.Text.Plain)

            }

        }
    }
    //start the server waiting for connection
    server.start(wait = true)
}





