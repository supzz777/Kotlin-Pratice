package com.example.jsonreadwrite.employees

class Employee {

    var employee_id: Int = 0
    var name: String = ""
    var age :Int = 0
    var gender: String = ""
    var department:String = ""


    constructor() : super() {}


    constructor(employee_id:Int , name:String ,age:Int,gender:String,department:String)
            : super() {
        this.employee_id = employee_id
        this.name = name
        this.age = age
        this.gender = gender
        this.department = department
    }

    override fun toString(): String {
        return "Employee(employee_id=$employee_id, name='$name', age=$age, gender='$gender', department='$department')"
    }


}