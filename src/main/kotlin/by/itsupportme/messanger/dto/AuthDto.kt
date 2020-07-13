package by.itsupportme.messanger.dto

import by.itsupportme.messanger.model.User

class AuthDto(
        val username: String = "",
        val password: String = ""
) {
        fun toUser(): User {
                return User(username, password = password)
        }
}