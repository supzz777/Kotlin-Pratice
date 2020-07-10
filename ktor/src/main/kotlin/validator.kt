package danny_sir_learnings

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import java.time.LocalDate
import java.time.Month
import java.time.format.DateTimeFormatter
import javax.validation.*
import kotlin.reflect.KClass


@Target(AnnotationTarget.FIELD)// specifies the possible kinds of elements which can be annotated with the annotation (classes, functions, properties, expressions etc.);
@Retention(AnnotationRetention.RUNTIME)//specifies whether the annotation is stored in the compiled class files and whether it's visible through reflection at runtime (by default, both are true);
@MustBeDocumented//specifies that the annotation is part of the public API and should be included in the class or method signature shown in the generated API documentation.
@Constraint(validatedBy = [DateValidator::class])
annotation class DateAfter(
    val date:String ="",
    val message: String = "invalid date", // returns the default key for creating error messages, this enables us to use message interpolation
    val groups: Array<KClass<out Any>> = [], // allows us to specify validation groups for our constraints
    val payload: Array<KClass<out Payload>> = [] //  can be used by clients of the Bean Validation API to assign custom payload objects to a constraint
)


//hard coded.
/*class DateValidator : ConstraintValidator<DateAfter, LocalDate> {
    override fun initialize(constraintAnnotation: DateAfter?) {}

    override fun isValid(value: LocalDate?, context: ConstraintValidatorContext?): Boolean =
        value?.let {
            (it > LocalDate.of(2020, Month.MAY, 2))
        } ?: false
}
*/

class DateValidator : ConstraintValidator<DateAfter, LocalDate> {
    private var date:String = ""
    private val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    override fun initialize(constraintAnnotation: DateAfter?) {
        this.date = constraintAnnotation?.date?:""
    }
    override fun isValid(value: LocalDate?, context: ConstraintValidatorContext?): Boolean =
        value?.let {
            val localDate = LocalDate.parse(date,dateFormatter)
     /*(object date, json class date)*/       (it > localDate)
        } ?: false
}

object ValidationFactory {
    val validator: Validator = Validation.buildDefaultValidatorFactory().validator
}

fun <E : Enum<E>, T> T.validateData(e: E): Either<CustomException, T> =
try {
    ValidationFactory.validator.validate(this)
        .let { violations ->
            val errors = mutableListOf<String>()
            violations.iterator().forEach { errors.add(it.message) }

            if (violations.size > 0)
                CustomException(errors[0]).left()
            else
                this.right()
        }
}catch (ex:CustomException){
    ex.left()
}
