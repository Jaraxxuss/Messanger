package by.itsupportme.messanger.security.jwt

import by.itsupportme.messanger.model.Role
import by.itsupportme.messanger.model.Status
import by.itsupportme.messanger.model.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.util.stream.Collectors

object JwtUserFactory {
    fun create(user: User): JwtUser {
        return JwtUser(
                user.username,
                user.password,
                mapToGrantedAuthorities(user.roles),
                user.status?.equals(Status.ACTIVE)
        )
    }

    private fun mapToGrantedAuthorities(userRoles: List<Role>): MutableList<GrantedAuthority> {
        return userRoles.stream()
                .map { SimpleGrantedAuthority(it.name) }.collect(Collectors.toList())
    }
}