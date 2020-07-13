package by.itsupportme.messanger.dto.util

import by.itsupportme.messanger.dto.AuthDto
import by.itsupportme.messanger.model.User

object AuthDtoUtil {
        fun from(user: User): AuthDto{
                return AuthDto(user.username,user.password)
        }
}