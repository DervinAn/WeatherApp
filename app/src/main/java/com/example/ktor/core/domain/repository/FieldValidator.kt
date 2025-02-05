package com.example.ktor.core.domain.repository

class FieldValidator {

    fun isFieldEmpty(field: String): Result<Unit, Error> {
         if(field.isEmpty()) {
            return Result.Error(Error.Validation.EMPTY_FIELD)
        }
        return Result.Success(Unit)
    }

}