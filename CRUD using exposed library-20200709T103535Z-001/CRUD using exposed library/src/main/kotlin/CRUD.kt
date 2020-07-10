
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction


//creating the table for using exposed library
object UserTable : /*IntId*/Table("user"){
    val id : Column<Int> = integer("id").autoIncrement().primaryKey()
    val firstName: Column<String> = varchar("firstName", length = 50)
    val lastName: Column<String> = varchar("lastName", length = 50)
    val email : Column<String> = varchar("email",length=50)
    val adress : Column<String> = varchar("adress", length = 100)
}

//database objector model to fill in the table
data class User( val id :Int,val firstName: String,val lastName: String,
                 val email: String, val adress:String)

// request object
data class UserDTO(
    val firstName: String,
    val lastName: String,
    val email: String,
    val adress: String
)

fun initDB() {
    val url = "jdbc:mysql://localhost:3306/ktor"
    val driver = "com.mysql.cj.jdbc.Driver"
    val username = "root"
    val password = "admin@123"
    Database.connect(url,driver,username,password)
}

class UserController {

    fun getAll(): ArrayList<User> {
        val users: ArrayList<User> = arrayListOf()
        transaction {
            UserTable.selectAll().map {
                users.add(
                    User(
                        id = it[UserTable.id],
                        firstName = it[UserTable.firstName],
                        lastName = it[UserTable.lastName],
                        email = it[UserTable.email],
                        adress = it[UserTable.adress]

                    )
                )
            }
        }
        return users
    }

    fun ResultRow.toUser(): User =
        User(
            this[UserTable.id],
            this[UserTable.firstName],
            this[UserTable.lastName],
            this[UserTable.email],
            this[UserTable.adress]
        )

    fun getBYid(id: Int):User =
        transaction {
            val user = UserTable.select {
                UserTable.id eq id
            }.singleOrNull() //if data exists with same idz then too we will get a single entry or else null.
            user?.toUser()!!
        }



    fun insert(user: UserDTO) {
        transaction {
            UserTable.insert {
                it[firstName] = user.firstName
                it[lastName] = user.lastName
                it[email] = user.email
                it[adress] = user.adress

            }
        }

    }


    fun update(user: UserDTO, id: Int) {
        transaction {
            UserTable.update({ UserTable.id eq id }) {
                it[firstName] = user.firstName
                it[lastName] = user.lastName
                it[email] = user.email
                it[adress] = user.adress
            }
        }
    }

    fun delete(id: Int) {
        transaction {
            UserTable.deleteWhere { UserTable.id eq  id }
        }
    }

}
val userController = UserController()

fun main(args: Array<String>) //Application entry point
{
    initDB()
    //implementing netty server
    val server = embeddedServer(Netty, 8080) {
        //Install the routing fetaure and starts configuring it

        routing {
            install(feature = ContentNegotiation) {
                gson {
                    setPrettyPrinting()
                }
            }
            //define a route which will handle specific path
            get("/") {
                call.respondText("Hello, world!", ContentType.Text.Html)
            }
            get("/userlist"){
                call.respond(userController.getAll())

            }

            get("/user/{id}"){
                val id :Int? = call.parameters["id"]?.toInt()
                if (id is Int) {
                    call.respond(userController.getBYid(id))
                }
            }

            post("/userinsert") {
                val userDto = call.receive<UserDTO>()
                userController.insert(userDto)
                call.respond(HttpStatusCode.Created)
            }
            put("/users/{id}") {
                val id :Int? = call.parameters["id"]?.toInt()
                val userDTO = call.receive<UserDTO>()
                if (id is Int) {
                    userController.update(userDTO, id)
                    call.respond(HttpStatusCode.OK)
                }


            }
            delete("/userdelete") {
                val id: Int? = call.request.queryParameters["id"]?.toInt()

                if (id is Int){
                    userController.delete(id)
                    call.respond(HttpStatusCode.OK)

                }


            }


        }
    }
    //start the server waiting for connection
    server.start(wait = true)
}







