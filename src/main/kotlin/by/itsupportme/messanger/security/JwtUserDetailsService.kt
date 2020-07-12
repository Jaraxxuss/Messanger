package by.itsupportme.messanger.security

import by.itsupportme.messanger.security.jwt.JwtUserFactory
import by.itsupportme.messanger.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class JwtUserDetailsService(
        @Autowired
        val userService: UserService
) : UserDetailsService {
        override fun loadUserByUsername(username: String?): UserDetails {
                val user = userService.findByUsername(username) ?: throw UsernameNotFoundException("User not found")
                return JwtUserFactory.create(user)
        }
}