package com.vayana.kotlin.bankmanagement

 open class Customer {

    var name:String= ""
    var account_type :String=""
    var account_num :Long = 0
    var account_balance =0


    constructor() : super() {}

    constructor(name: String, account_type: String, account_num: Long, account_balance: Int)
                    :super(){
        this.name = name
        this.account_type = account_type
        this.account_num = account_num
        this.account_balance = account_balance
    }

    override fun toString(): String {
        return "Customer(name='$name', account_type='$account_type', " +
        "account_num=$account_num, account_balance=$account_balance)"
    }


}