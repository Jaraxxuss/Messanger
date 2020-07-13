package by.itsupportme.messanger.controllers

import by.itsupportme.messanger.dto.util.UserDtoUtil
import by.itsupportme.messanger.model.User
import by.itsupportme.messanger.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.stream.Collectors
import java.util.stream.StreamSupport

@RestController
@RequestMapping("/users")
class UserController(
        @Autowired
        private val userService: UserService
) {

    @GetMapping("all")
    fun list(): ResponseEntity<Any> {

        return ResponseEntity.ok(StreamSupport.stream(userService.findAll().spliterator(), false)
                .map { user: User -> UserDtoUtil.from(user) }
                .collect(Collectors.toList()))
    }

    @GetMapping("{id}")
    fun getById(@PathVariable(value = "id") id: Long): ResponseEntity<Any> {
        val user = userService.findById(id)
        user ?: return ResponseEntity.noContent().build()
        return ResponseEntity.ok(UserDtoUtil.from(user))
    }

}