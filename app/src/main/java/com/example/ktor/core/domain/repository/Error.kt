package com.example.ktor.core.domain.repository

sealed interface Error {

    enum class Network: Error {
        REQUEST_TIMEOUT,
        NO_INTERNET_CONNECTION,
        SERVER_ERROR
    }

    enum class Validation: Error {
        EMPTY_FIELD,
        INVALID_EMAIL,
        INVALID_PASSWORD
    }
    enum class Local: Error {
        NO_DATA
    }
    enum class Unexpected: Error {
        UNKNOWN_ERROR
    }
}



