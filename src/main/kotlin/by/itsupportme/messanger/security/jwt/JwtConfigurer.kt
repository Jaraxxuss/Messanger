package by.itsupportme.messanger.security.jwt

import by.itsupportme.messanger.security.jwt.filters.JwtTokenFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class JwtConfigurer(
        @Autowired
        private val jwtTokenProvider: JwtTokenProvider
) : SecurityConfigurerAdapter<DefaultSecurityFilterChain?, HttpSecurity>() {
        @Throws(Exception::class)
        override fun configure(httpSecurity: HttpSecurity) {
                val jwtTokenFilter = JwtTokenFilter(jwtTokenProvider)
                httpSecurity.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter::class.java)
        }

}