package com.example.ktor.core.domain.repository


/**
 typealias i use it because Error name does alerdy exist in kotlin and i wont use keyword Error
 another time , so i did use to simplify when i will use them
 (explain : i will not chose what Error what i mean at least will be just 2 or 3)
 for this file
 **/
typealias RootError =  Error

sealed interface Result<out D,out E:RootError> {
    data class Success<out D,out E:RootError>(val data: D) : Result<D, E>
    data class Error<out D,out E:RootError>(val error: E) : Result<D, E>
}