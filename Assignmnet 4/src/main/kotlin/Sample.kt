/*
import arrow.core.*
import arrow.core.extensions.fx
import arrow.fx.IO
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.post
import io.ktor.http.ContentType

import io.ktor.http.content.TextContent
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.lang.Exception

object Abc:Table("abc"){
    val id = integer("id").primaryKey()
    val name = varchar("name",20)
}
data class Clss(val id:Int, val name:String)

fun main() {
       val url = "jdbc:mysql://localhost:3306/mysql_demo"
       val username = "root"
       val password = "Vishnu_30"
    Database.connect(url,"com.mysql.cj.jdbc.Driver",username,password)
    transaction {
//        Abc.insert{
//            it[id] = 1
//            it[name] = "vishnu"
//        }
    }
    server()
//    val clss = getClss()
//    print(clss)
}

fun ResultRow.toClss() =
    Clss(
        this[Abc.id],
        this[Abc.name]
    )

fun getClss():Either<Exception,Option<Clss>> =
    Either.fx {
            transaction {
                val result = Abc.select {
                    Abc.id eq 2
                }.singleOrNone() // even if we have multiple entries in our database it
                                   //     should return only single data set so we are throwing
                                    //     the exception. In case of multiple data set it will return nothing.
               result.fold({None},{
                   val clss =  it.toClss()
                   clss.toOption()
               })
            } //By using fold we decide what to return if exception is happened and what to return
               // in case of no exception
        }



C
val client = HttpClient(CIO)
val mapperOb = ObjectMapper().registerKotlinModule()

fun server(){
//    IO.effect {
//        val result = client.post<String>("/"){
//            body = mapperOb.readValue(abc.toString(),Clss::class.java)
//        }.let {
//            "success".right()
//        }
//        print("hehe $result")

//        client.post<String>("https://preproduction.signzy.tech/api/v2/patrons/login") {
//            body = mapperOb.readValue(abc.toString(),Clss::class.java)
//        }.let {
//
//            mapper.readValue(it, Clss::class.java).right()
//        }
//    }
    val s = embeddedServer(Netty,port = 8080){
        routing {
            get("/"){
                val clss = getClss()
                call.respondText(clss.toString(),ContentType.Text.Html)
            }
        }
    }
    s.start(true)
}



fun Application.module() {
    embeddedServer(Netty,port = 8080){
        routing {
            get("/"){
                call.respondText("hello",ContentType.Text.Html)
            }
        }
    }
}



*/