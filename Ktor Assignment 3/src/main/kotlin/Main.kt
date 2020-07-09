
import com.google.gson.Gson
import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction


object Person : /*IntId*/Table("person"){
    val id : Column<Int> = integer("id").autoIncrement()
    val firstName: Column<String> = varchar("firstName", length = 50)
    val lastName: Column<String> = varchar("lastName", length = 50)
    val email : Column<String> = varchar("email",length=50)
    val adress : Column<String> = varchar("adress", length = 100)
}




data class Persons( val id :Int,val firstName: String,val lastName: String,
                   val email: String, val adress:String)




fun initDB() {
    val url = "jdbc:mysql://localhost:3306/ktor"
    val driver = "com.mysql.cj.jdbc.Driver"
    val username = "root"
    val password = "admin@123"
    Database.connect(url,driver,username,password)
}

fun getTopuserData(): String {
    var json: String = ""
    transaction {
        val result = Person.selectAll().orderBy(Person.id, false).limit(100)
        val persons = ArrayList<Persons>()
        for (i in result) {

            persons.add(Persons( id =i[Person.id],firstName = i[Person.firstName],
                lastName =  i[Person.lastName], email = i[Person.email] , adress = i[Person.adress] ) )
        }

        json = Gson().toJson(persons);


    }
    return json
}



fun insertUserData(): String {var json: String = ""
    transaction {
        val id = Person.insert/*AndGetId */{
            it[id]=3
            it[firstName] = "Pooja"
            it[lastName] = "Waghmare"
            it[email] = "pooja@gmail.com"
            it[adress] = "Trombay Mumbai-88"
        }
       // val result:Query= Person.insert{}

       /* val persons = ArrayList<Persons>()
        for (i in result) {

            persons.add(Persons( firstName = i[Person.firstName],
                lastName =  i[Person.lastName], email = i[Person.email] , adress = i[Person.adress] ))
        }

        json = Gson().toJson(persons);
        */
    }
    return "YES"
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
            get("/list"){
                call.respondText(getTopuserData(), ContentType.Text.Plain)
            }
            post("/insertuser"){
                call.respondText(insertUserData(), ContentType.Text.Plain)

            }

        }
    }
    //start the server waiting for connection
    server.start(wait = true)
}