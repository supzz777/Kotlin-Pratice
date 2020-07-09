package com.vayana.sorting.FileRead


import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import java.io.File
import java.io.FileReader
import java.io.FileWriter








fun main(args: Array<String>) {


    val filename = "employees.json"


    readJSONFile(filename)

    writeJSONtoFile(filename)

    readJSONFile(filename)


    //=======================================================//

    fun readJSONFile(s: String) {

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


    //=======================================================//

    fun writeJSONtoFile(s: String) {


/*      //simply writes the file by erasing the previous data.

        //Create a Object of Post
         var employee = Employees(11,"ashish jadhav",
                 26,"Male","IT")
        //Create a Object of Gson
        var gson = Gson()
        //Convert the Json object to JsonString
        var jsonString:String = gson.toJson(employee)

        //Initialize the File Writer and write into file
        val file=File(s)
        file.writeText(jsonString)

         println("_____Json File Write Completed _____")
 */
        //======================================//
        /*
         var bw: BufferedWriter? = null
         var fw: FileWriter? = null
         val filename = "employees.json"

         try{

             //Create a Object of Employees Class
             var employee = Employees(11,"ashish jadhav",
                     26,"Male","IT")

             val file = File(filename)

             // true = append file
             fw = FileWriter(file.absoluteFile, true)
             bw =  BufferedWriter(fw);
             
             bw.write(employee)
             println("_____Json File Write Completed _____")

         }
         catch(e:Exception)
         {
             e.printStackTrace()
         }
 */
        //======================================//
/*
    try{
         val parser = JsonParser()
        // val obj: Any = parser.parse(FileReader("employees.json"))
       //  var jsonObject = obj as JsonObject

        val array: JsonArray = parser.parse(FileReader
        ("employees.json")) as JsonArray
       var  jsonObject =array.asJsonObject
        val msg = jsonObject.get("employee")

         val file = FileWriter("employees.json", false)
         val jw = JsonWriter(file)

         //Create a Object of Employees Class
         var employee = Employees(11,"ashish jadhav",
                 26,"Male","IT")

         var gson = Gson()
         gson.toJson(employee, Employees::class.java, jw)
         file.close()
         println("_____Json File Write Completed _____")

        }
        catch(e: Exception) {
        e.printStackTrace();
        }
*/

        //====================================//

        val jsonParser = JSONParser()

        try {

            val obj = jsonParser.parse(FileReader
            ("employees.json"))

            val jsonArray: JSONArray = obj as JSONArray

            //Create a Object of Employees Class
            // var employee:JSONArray = Employees(11,"ashish jadhav",
            //        26,"Male","IT")

            var employee = JSONObject()
            employee.put("employee_id", Integer(6));
            employee.put("name", "Brack");
            employee.put("age", Integer(33));
            employee.put("gender", "Male");
            employee.put("deartment", "Marketing");

            val file = FileWriter("employees.json")
            file.write(jsonArray.toJSONString());
            //file.flush();
            file.close();

        } catch (e: Exception) {
            e.printStackTrace()

        }


    }

}


//=====================================================//


