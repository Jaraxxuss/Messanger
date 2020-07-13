package by.itsupportme.messanger.security.jwt.filters

import by.itsupportme.messanger.security.jwt.JwtAuthenticationException
import by.itsupportme.messanger.security.jwt.JwtTokenProvider
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.filter.GenericFilterBean
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest


class JwtTokenFilter(
        private val jwtTokenProvider: JwtTokenProvider

) : GenericFilterBean() {
    @Throws(IOException::class, ServletException::class)
    override fun doFilter(req: ServletRequest?, res: ServletResponse?, filterChain: FilterChain) {
        val token = jwtTokenProvider.resolveToken((req as HttpServletRequest?)!!)
        try {
            if (token != null && jwtTokenProvider.validateToken(token)) {
                try {
                    SecurityContextHolder.getContext().authentication = jwtTokenProvider.getAuthentication(token)
                } catch (e: UsernameNotFoundException) {

                }

            }
        } catch (e: JwtAuthenticationException) {

        }

        filterChain.doFilter(req, res)
    }
}