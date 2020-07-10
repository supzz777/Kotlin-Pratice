package danny_sir_learnings

import java.lang.Exception

data class CustomException(override val message: String?): Exception(message)