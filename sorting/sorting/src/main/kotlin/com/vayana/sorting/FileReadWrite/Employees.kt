package com.vayana.sorting.FileReadWrite

class Employees {

    var employee_id:Int?= null
    var name:String?= null
    var age:Int?= 0
    var gender:String?= null
    var department:String?= null

    constructor() : super() {}


    constructor(employee_id:Int , name:String ,age:Int,gender:String,department:String)
            : super() {
        this.employee_id = employee_id
        this.name = name
        this.age = age
        this.gender = gender
        this.department = department
    }

    // in kotlin we dont need a getter or setter in our pojo class.
}