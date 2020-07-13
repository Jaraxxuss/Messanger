package by.itsupportme.messanger.dto.util

import by.itsupportme.messanger.dto.UserDto
import by.itsupportme.messanger.model.User

object UserDtoUtil {
    fun from(user: User) = UserDto(user.username)
}