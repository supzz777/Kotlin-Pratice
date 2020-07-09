package com.vayana.sorting.FileReadWrite

import com.google.gson.Gson
import com.google.gson.JsonParser
import com.vayana.sorting.FileRead.Employees
import org.json.JSONObject
import java.io.*


fun main(args:Array<String>) {

    var filename :String = "employees.json"

    jsonWrite(filename)
    jsonRead(filename)
}
//==========================================================================================//
fun jsonRead(filename: String) {
    var br: BufferedReader? = null
    try{
        br =  BufferedReader( FileReader
        ("C:\\Users\\DELL\\Desktop\\Kotlin Pratice\\sorting\\sorting\\employees.json"));
        var contentLine = br.readLine()

        while (contentLine != null) {
            System.out.println(contentLine);
            contentLine = br.readLine();
        }

        println("___________JSON file Read Completed____________")
    }
    catch ( ioe :Exception)
    {
        ioe.printStackTrace();
    }



}

//==========================================================================================//

fun jsonWrite(filename: String) {

    var filename :String = "employees.json"
    var bw:BufferedWriter? = null
    try {

        var strFileJson: String = getStringFromFile(filename.toString())
        var jsonObj = JSONObject(strFileJson)
        val gson = Gson()
        val jsonParser = JsonParser()

        //Create a Object of Employees Class
        var employeez = Employees(11, "ashish jadhav",
                26, "Male", "IT")

        val jsonStr = jsonParser.parse(gson.toJson(employeez)).toString()
         jsonObj = JSONObject(jsonStr)


        val file:File = File("C:\\Users\\DELL\\Desktop\\Kotlin Pratice\\sorting\\sorting\\employees.json")


        writeJsonFile(file , jsonObj.toString());

        println("____________json FIle write Completed")

    }
    catch (ioe:Exception)
    {
        ioe.printStackTrace();
    }


}
//==============================================================================================//
fun writeJsonFile(file: File, json: String) {

    var bufferedWriter: BufferedWriter? = null
    try{

        val fileWriter = FileWriter(file)
        bufferedWriter =  BufferedWriter(fileWriter);
        bufferedWriter.write(json);



    }
    catch (e:Exception){
       e.printStackTrace()
    }

}

//===============================================================================================//
fun getStringFromFile(filename: String): String {

    val fl = File(filename)
    val fin = FileInputStream(fl)
    var ret: String = convertStreamToString(fin)
    fin.close()
    return ret

}
//================================================================================================//
fun convertStreamToString(fin: FileInputStream): String {
    var reader = BufferedReader(InputStreamReader(`fin`))
    val sb = StringBuilder()
    var line: String? = reader.readLine()
    while (line != null){
        sb.append(line).append("\n");
    }

    return sb.toString()
}
//================================================================================================//
