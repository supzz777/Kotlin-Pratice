package com.vayana.sorting.FileRead


import com.google.gson.Gson
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import java.io.BufferedWriter
import java.io.File
import java.io.FileReader
import java.io.FileWriter


fun main(args:Array<String>) {

    val filename = "employees.json"

    readJSONFile(filename)
    writeJSONtoFile(filename)

}
//==========================================================================================================//

fun readJSONFile(filename: String) {

    try {

        val filename = "employees.json"
        var lines: List<String> = File(filename).readLines()
        lines.forEach { line -> println(line) }
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        println("_____Json File Read Completed _____")
    }

}
//==========================================================================================================//

fun writeJSONtoFile(filename: String) {

    //Create a Object of Employees Class
    var employeez = Employees(11, "ashish jadhav",
            26, "Male", "IT")

    val jsonParser = JSONParser()

    val obj:Any = jsonParser.parse(FileReader
    ("employees.json"))

     val jsonObject = obj as JSONObject

    jsonObject.get("employeez")


    val gson = Gson()
    //Convert the Json object to JsonString
    var jsonString: String = gson.toJson(employeez)

    var writer: BufferedWriter? = null
    try {

        writer = BufferedWriter(FileWriter("employees.json"));
        writer.write(jsonString)
        println("_____Json File Write Completed _____")


    } catch (e: Exception) {

        e.printStackTrace()
    }




}
