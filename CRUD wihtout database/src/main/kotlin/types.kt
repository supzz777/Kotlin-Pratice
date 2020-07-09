data class PersonReq(
    val emailId: String,
    val firstName: String,
    val lastName: String,
    val city: String,
    val state: String,
    val country: String
){
    fun toPersonObj(id: Int) =
        Person(id, emailId, firstName, lastName, city, state, country)
}
​
data class PersonRes(
    val id: Int,
    val emailId: String,
    val firstName: String,
    val lastName: String,
    val city: String,
    val state: String,
    val country: String,
    val status: String,
    val callId: String
)
​
data class ResponseClass(
    val status: String,
    val message:String,
    val data: Any?
)