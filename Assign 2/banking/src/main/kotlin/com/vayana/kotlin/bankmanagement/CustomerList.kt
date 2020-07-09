package com.vayana.kotlin.bankmanagement

class CustomerList {

    var customers:List<Customer> = listOf()

    constructor() : super() {}

    constructor(customers: List<Customer>) {
        this.customers = customers
    }


    override fun toString(): String {
        return "CustomerList(customers=$customers)"
    }


}