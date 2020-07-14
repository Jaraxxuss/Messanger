package by.itsupportme.messanger.configs

import by.itsupportme.messanger.controllers.AuthController
import by.itsupportme.messanger.security.jwt.JwtConfigurer
import by.itsupportme.messanger.security.jwt.JwtTokenProvider
import by.itsupportme.messanger.security.jwt.filters.JwtTokenFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@EnableWebSecurity
class WebSecurityConfig(
        @Autowired
        val bCryptPasswordEncoder: BCryptPasswordEncoder,

        @Autowired
        val jwtTokenProvider: JwtTokenProvider,

        @Autowired
        @Qualifier("jwtUserDetailsService")
        val userDetailsService: UserDetailsService

) : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity?) {
        http
                ?.cors()
                ?.and()
                ?.csrf()
                ?.disable()?.authorizeRequests()
                ?.antMatchers("/login", "/register")
                ?.permitAll()
                ?.anyRequest()?.authenticated()
                ?.and()
                ?.apply(JwtConfigurer(jwtTokenProvider))
                ?.and()
                ?.sessionManagement()?.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                ?.and()
                ?.logout()?.logoutSuccessUrl("/")
                ?.permitAll()

    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.userDetailsService(userDetailsService)?.passwordEncoder(bCryptPasswordEncoder)
    }

    @Bean
    @Throws(Exception::class)
    override fun authenticationManagerBean(): AuthenticationManager? = super.authenticationManagerBean()


}