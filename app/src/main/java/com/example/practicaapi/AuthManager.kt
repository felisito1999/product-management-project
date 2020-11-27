package com.example.practicaapi

public class AuthManager() {
    public fun checkEmptyFields(email: String, password: String) : Boolean {
        return !email.isNullOrBlank() && !password.isNullOrBlank()
    }

    public fun validateEmail(email: String) : Boolean {

        var atCount :Int = 0

        val minimumLength = 10
        val emailLength = email.length

        email.forEach { Char ->
            if (Char === '@'){
                atCount += 1
            }
        }

        return minimumLength <= emailLength && atCount == 1
    }

    public fun validatePassword(password: String) : Boolean {
        val minimumLength = 6

        return minimumLength <= password.length
    }
}