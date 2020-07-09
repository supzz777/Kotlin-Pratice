package com.vayana.kotlin.bankmanagement

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import java.io.File
import java.util.*
import kotlin.collections.ArrayList


class CustomerDetails (name: String, account_type: String, account_num: Long,
          account_balance: Int) :
    Customer (name = name, account_type = account_type,
        account_num = account_num, account_balance = account_balance)
{

    val sc = Scanner(System.`in`)
    var filename: String = "C:\\Users\\DELL\\Desktop\\Kotlin Pratice\\Assign 2\\banking\\account_customers.json"

    val mapper = jacksonObjectMapper().registerKotlinModule()

    var model = CustomerList()

    var customerList: ArrayList<Customer> = ArrayList<Customer>()

    var customer = Customer()


    fun insert(namez:String, account_typez: String, account_numz: Long, account_balancez: Int)
    {
        // input user name, account type , account number and account balance
        /*
        print(namez)
        print(account_typez)
        print(account_numz)
        print(account_balancez)
        */


        model = mapper!!.readValue<CustomerList>(File("C:\\Users\\DELL\\Desktop\\Kotlin Pratice\\Assign 2\\banking\\account_customers.json"))

        //println(model)

        //println("_______Json Read Completed_______")


        customerList.addAll(model.customers)



            name=namez
            account_type=account_typez
            account_num = account_numz
            account_balance = account_balancez

          //  println("$name, $account_type ,$account_num ,$account_balance")

                customer.name =name
                customer.account_type = account_type
                customer.account_num = account_num
                customer.account_balance= account_balance

//println("${customer.name},${customer.account_type},${customer.account_num},${customer.account_balance}")


        customerList.add(customer)

       // println(customerList)

        model.customers=customerList
       // println("customerlist==> $customerList")
      //  println("model==> $model")


        mapper.writerWithDefaultPrettyPrinter().writeValue(
            File("C:\\Users\\DELL\\Desktop\\Kotlin Pratice\\Assign 2\\banking\\account_customers.json"),model)

        println("_______Your Bank Account Has Been Successfully Created..._______")


    }

    //==============================================================================================================//

    fun search(account_numz: Long):Boolean{
        model = mapper!!.readValue<CustomerList>(File("C:\\Users\\DELL\\Desktop\\Kotlin Pratice\\Assign 2\\banking\\account_customers.json"))

        customerList.addAll(model.customers)

        for(i in 0 .. customerList.size)
        {
            if( account_numz.compareTo(model.customers.get(i).account_num)==0){

                println("Welcome MR/Mrs ${model.customers.get(i).name}")
                return true

            }
        }
        return false

    }

    //===============================================================================================================//

    fun deposit(depositamount: Int, account_numz: Long) {

        model =
            mapper!!.readValue<CustomerList>(File("C:\\Users\\DELL\\Desktop\\Kotlin Pratice\\Assign 2\\banking\\account_customers.json"))

        for (i in 0 until (customerList.size ))
        {

            if ( account_numz == model.customers.get(i).account_num )
            {

                var accountbalance = model.customers.get(i).account_balance

                model.customers.get(i).account_balance=(accountbalance+depositamount)


                mapper.writerWithDefaultPrettyPrinter().writeValue(
                    File("C:\\Users\\DELL\\Desktop\\Kotlin Pratice\\Assign 2\\banking\\account_customers.json")
                    , model )


                println("Your Money Has Been Deposied Successfully")
                println("Your Remaining Account Balance IS --> ${model.customers.get(i).account_balance}")

                break

            }



        }


    }

 //=================================================================================================================//

    fun withdraw(account_numz: Long) {

        var withdrawamount:Int=0

        model =
            mapper!!.readValue<CustomerList>(File("C:\\Users\\DELL\\Desktop\\Kotlin Pratice\\Assign 2\\banking\\account_customers.json"))

        for (i in 0 until ( customerList.size) ) {

            if ( account_numz == model.customers.get(i).account_num )
            {

                            if(model.customers.get(i).account_balance==0)
                                println("Your Accout Is Empty..")
                            else{
                                println("Enter the Amount You Want To Withdraw")
                                 withdrawamount = sc.nextInt()
                            }

                    if(withdrawamount >= model.customers.get(i).account_balance) {
                        println("You Cant Withdraaw The Entered Amount ... Please Try with Another Amount..")
                        break
                    }
                    else {

                        var accountbalance = model.customers.get(i).account_balance

                        model.customers.get(i).account_balance=(accountbalance - withdrawamount)

                        println("Your Have Withdrawn Your Money Successfully")

                        println("Your Remaining Account Balance IS --> ${model.customers.get(i).account_balance}")

                        mapper.writerWithDefaultPrettyPrinter().writeValue(
                            File("C:\\Users\\DELL\\Desktop\\Kotlin Pratice\\Assign 2\\banking\\account_customers.json"),
                            model )

                        break

                    }

            }

        }



    }
 //=======================================================================================================================//

    fun displaybalance(account_numz: Long){

        model =
            mapper!!.readValue<CustomerList>(File("C:\\Users\\DELL\\Desktop\\Kotlin Pratice\\Assign 2\\banking\\account_customers.json"))

        for (i in 0 until ( customerList.size) ) {

            if (account_numz == model.customers.get(i).account_num) {

                println("Your Account Balance is --> ${model.customers.get(i).account_balance}")
                println("Thankyou Please Visit Us Again..")
                break


            }

        }
    }
//=====================================================================================================================//

 fun deactivate_account(account_numz: Long){

     model =
         mapper!!.readValue<CustomerList>(File("C:\\Users\\DELL\\Desktop\\Kotlin Pratice\\Assign 2\\banking\\account_customers.json"))

     for (i in 0 until ( customerList.size) ) {

         if (account_numz == model.customers.get(i).account_num) {

             customerList.removeAt(i)

             println(customerList)

             model.customers = customerList



            mapper.writerWithDefaultPrettyPrinter().writeValue(
                 File("C:\\Users\\DELL\\Desktop\\Kotlin Pratice\\Assign 2\\banking\\account_customers.json")
                 , model )

             println("Your Account Has Been Successfully DeActivated....")

             break

         }

     }
 }
//=====================================================================================================================//


}