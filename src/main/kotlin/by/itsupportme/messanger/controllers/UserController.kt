package by.itsupportme.messanger.controllers

import by.itsupportme.messanger.dto.UserDto
import by.itsupportme.messanger.model.User
import by.itsupportme.messanger.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.stream.Stream

@RestController
@RequestMapping("/users")
class UserController(
        @Autowired
        private val userService: UserService
) {

        @GetMapping("all")
        fun list() : Iterable<User>{
                return userService.findAll()
        }

        @GetMapping("{id}")
        fun getById(@PathVariable(value = "id") id: Long) : User?{
                return userService.findById(id)
        }
}