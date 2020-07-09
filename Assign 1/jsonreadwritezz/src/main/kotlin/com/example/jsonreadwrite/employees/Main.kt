package com.example.jsonreadwrite.employees

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import java.io.File


fun main(args: Array<String>) {

    var filename: String = "C:\\Users\\DELL\\Desktop\\Kotlin Pratice\\jsonreadwrite\\employees.json"
    var fileout: String = "C:\\Users\\DELL\\Desktop\\Kotlin Pratice\\jsonreadwrite\\employeeswrite.json"

    val mapper = jacksonObjectMapper().registerKotlinModule()

    var model = Employees()

    model = mapper!!.readValue<Employees>(File("employees.json"))


    println("_______Json Read Completed_______")

    var employeeList: ArrayList<Employee> = ArrayList<Employee>()




    employeeList.addAll(model.employees)

    var employee = Employee()

    employee.employee_id=11
    employee.name= "Ashish"
    employee.age=26
    employee.gender="Male"
    employee.department="Engineering"



    employeeList.add(employee)


    //sorting the data as per the ID
    val sortedList = ArrayList(employeeList.sortedWith(compareBy(Employee::employee_id)) )

       println(sortedList)


    model.employees = sortedList


   //writing into file.
    //check the file for result.
   mapper.writerWithDefaultPrettyPrinter().writeValue(
           File("employeeswrite.json"),model)

    println("_______Json Write and Sort Completed_______")


}

//===================================================================//

