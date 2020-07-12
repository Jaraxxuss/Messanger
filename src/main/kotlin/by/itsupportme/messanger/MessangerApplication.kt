package by.itsupportme.messanger

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@SpringBootApplication
class MessangerApplication{
    @Bean
    fun bCryptPasswordEncoder() : BCryptPasswordEncoder{
        return BCryptPasswordEncoder()
    }
}

fun main(args: Array<String>) {
    runApplication<MessangerApplication>(*args)
}


