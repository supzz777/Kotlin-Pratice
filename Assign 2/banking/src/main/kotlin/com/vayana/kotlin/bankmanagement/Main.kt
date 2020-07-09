package com.vayana.kotlin.bankmanagement

import java.util.*

fun main(args: Array<String>) {


    var user_name :String = ""
    var account_type:String = ""
    var account_balance :Int =0
    var withdraw:Int = 0
    var deposit:Int = 0

    val sc = Scanner(System.`in`)
    var quit = false

    var customer = CustomerDetails("","",0,0)


    do {
        System.out.println("Welcome to SUPZZ Bank Sir/Mam.....");
        println()
        System.out.println("1. Create Account");
        System.out.println("2. Deposit money");
        System.out.println("3. Withdraw money");
        System.out.println("4. Check balance");
        System.out.println("5. Deactivate Account");
        System.out.println("6. Exit: \n");

        System.out.println("Please Enter Your Choice : ");

        var userChoice = sc.nextInt()

        when (userChoice) {

             1 -> {  //Creating The Account....
                 System.out.println("Enter your Name : ");
                 user_name=sc.next()

                 System.out.println("Enter Accout Type : Savings/Current");
                  account_type = sc.next()

                 var account_num :Long =getRandomNumber()
                 println("Your account number is -----> $account_num")

                 println("Please Wait...................")

                 customer.insert(user_name,account_type,account_num,account_balance)

             }
             2 ->{ //Depositing The Amount Into The Account...
                 println("Enter your Account Number please")
                 var accountnumber :Long= sc.nextLong()
                    //Searching if the account number is present in our record..
                if( customer.search(accountnumber))
                {
                    print("Enter The Amount Of Money You Want To Deposit... : ")
                    var depositamount = sc.nextInt()
                    customer.deposit(depositamount,accountnumber )

                }
                 else{
                    println("Sorry .. Your Account is Wrong..Please Try Again Later...")
                }

             }
             3->{//Withdrawing The Amount From The Account...
                 println("Enter your Account Number please")
                 var accountnumber :Long= sc.nextLong()
                 //Searching if the account number is present in our record..
                 if( customer.search(accountnumber))
                 {
                     customer.withdraw(accountnumber)

                 }
                 else{
                     println("Sorry .. Your Account is Wrong..Please Try Again Later...")
                 }

             }
             4->{//Check The Balance
                 println("Enter your Account Number please")
                 var accountnumber :Long= sc.nextLong()
                 //Searching if the account number is present in our record..
                 if( customer.search(accountnumber))
                 {
                     customer.displaybalance(accountnumber)

                 }
                 else{
                     println("Sorry .. Your Account is Wrong..Please Try Again Later...")
                 }

             }
             5->{  //Deactivating The Account
                 println("Enter your Account Number please")
                 var accountnumber :Long= sc.nextLong()
                 //Searching if the account number is present in our record..
                 if( customer.search(accountnumber))
                 {
                     customer.deactivate_account(accountnumber)

                 }
                 else{
                     println("Sorry .. Your Account is Wrong..Please Try Again Later...")
                 }
             }
             6 ->{
                 quit = true;
             }
             else -> {
                 println("Wrong Choice")
                 println()
             }


        }

    }while (!quit)
    System.out.println("Thanks !! See you again Byee....");

}
//===============================================================================================================//
//generate 16 digit random number..
fun getRandomNumber():Long{
    var aNumber:Long =0
    aNumber = ((Math.random() * 9000000000000000)+1000).toLong();

    // print(aNumber)
    return aNumber

}

//==============================================================================================================//
/*
fun insert(namez:String, account_typez: String, account_numz: Long, account_balancez: Int)

{
    // input user name, account type , account number and account balance

    var filename: String = "C:\\Users\\DELL\\Desktop\\Kotlin Pratice\\Assign 2\\banking\\account_customers.json"

    val mapper = jacksonObjectMapper().registerKotlinModule()

    var model = CustomerList()

    model = mapper!!.readValue<CustomerList>(File("C:\\Users\\DELL\\Desktop\\Kotlin Pratice\\Assign 2\\banking\\account_customers.json"))

    //println(model)

    println("_______Json Read Completed_______")

    var customerList: ArrayList<Customer> = ArrayList<Customer>()

    customerList.addAll(model.customers)

    var customer = Customer()

    customer.name =namez
    customer.account_type = account_typez
    customer.account_num = account_numz
    customer.account_balance= account_balancez


    customerList.add(customer)

    mapper.writerWithDefaultPrettyPrinter().writeValue(
        File("C:\\Users\\DELL\\Desktop\\Kotlin Pratice\\Assign 2\\banking\\account_customers.json"),model)

    println("_______Customer Successfully Inserted Into Json File_______")


}
*/
//==================================================================================================================//