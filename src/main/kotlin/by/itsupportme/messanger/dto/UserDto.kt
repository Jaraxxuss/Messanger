package by.itsupportme.messanger.dto

import by.itsupportme.messanger.model.User

class UserDto (
        val username: String
) {
    fun toUser() = User(username)
}