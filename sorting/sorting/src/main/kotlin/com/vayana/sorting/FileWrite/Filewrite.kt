package com.vayana.sorting.FileWrite

import org.json.simple.JSONObject
import java.io.FileWriter


fun main(args:Array<String>) {

    /*
    try {
        val mapper = ObjectMapper()
        //Create a Object of Employees Class
        var employeez = Employees(11, "ashish jadhav",
                26, "Male", "IT")

        val writer: ObjectWriter = mapper.writer(DefaultPrettyPrinter())

        // convert book object to JSON file
        writer.writeValue(Paths.get("employees.json").toFile(), employeez);

        println("________Json File WRITE Completed__________")
    }
    catch (e:Exception){
        e.printStackTrace()
    }
    */

    //================//
    /*
    val filename:String = "C:\\Users\\DELL\\Desktop\\Kotlin Pratice\\sorting\\sorting\\employees.json"
    val obj = JSONObject()
    obj.put("employee_id ", "11");
    obj.put("name ", "Ashish Jadhav");
    obj.put("age ", "26");
    obj.put("gender ", "Male");
    obj.put("department ", "IT");

    var file:FileWriter = FileWriter(filename, true)

    try
    {

        file.write(obj.toJSONString())
        file.write(",")
        file.append("\n")
        file.flush()

        println("________Json File WRITE Completed__________")

    }
    catch (e: IOException) {
        e.printStackTrace();
    }
    */

    //==================//

    val obj = JSONObject()
    var add = obj.add("employee_id", "11")
    obj.put("name ", "Ashish Jadhav");
    obj.put("age ", "26");
    obj.put("gender ", "Male");
    obj.put("department ", "IT");

    var file  :FileWriter =  FileWriter("C:\\Users\\DELL\\Desktop\\Kotlin Pratice\\sorting\\sorting\\employees.json")
    try
    {
        file.write(obj.toJSONString());
        file.flush();

    }
    catch(e:Exception){
    e.printStackTrace()
    }



    }

//=====================================================================================//

