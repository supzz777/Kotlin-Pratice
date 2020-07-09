package com.example.jsonreadwrite.employees

class Employees {

     var employees: List<Employee> = listOf()


    constructor() : super() {}

    constructor(employees: List<Employee>){

        this.employees=employees
    }

    override fun toString(): String {
        return "Employees(employees=$employees)"
    }


}