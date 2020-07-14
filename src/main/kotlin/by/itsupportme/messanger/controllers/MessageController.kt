package by.itsupportme.messanger.controllers

import by.itsupportme.messanger.model.Message
import by.itsupportme.messanger.model.Status
import by.itsupportme.messanger.model.User
import by.itsupportme.messanger.security.jwt.JwtTokenProvider
import by.itsupportme.messanger.services.MessageService
import by.itsupportme.messanger.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("\${mapping.messages}")
class MessageController(
        @Autowired
        val messageService: MessageService,
        @Autowired
        val jwtTokenProvider: JwtTokenProvider,
        @Autowired
        val userService: UserService
) {
    @GetMapping
    fun all(): ResponseEntity<Iterable<Message>> = ResponseEntity.ok(messageService.findAllActive())

    @PostMapping
    fun add(@RequestHeader("\${jwt.token.authority}") bearerToken: String, @RequestBody body: Map<String, String>) {
        val token = jwtTokenProvider.resolveToken(bearerToken)
        val user = userService.findByUsername(jwtTokenProvider.getUsername(token))
        val message = user?.let { Message(it, body["content"].toString()) }
        message?.let { messageService.createMessage(it) }
    }

    @PutMapping("\${mapping.id}")
    fun update(@RequestBody body: Map<String, String>, @PathVariable("\${pathvariable.id}") id: Long) {
        val messageFromDao = messageService.findById(id)
        if (messageFromDao != null) {
            messageFromDao.content = body["content"].toString()
            messageService.updateMessage(messageFromDao)
        }
    }

    @GetMapping("\${mapping.id}")
    fun getOne(@PathVariable("\${pathvariable.id}") id: Long): Message? = messageService.findById(id)

    @DeleteMapping("\${mapping.id}")
    fun delete(@PathVariable("\${pathvariable.id}") id: Long) {
        val message = messageService.findById(id)
        if (message != null) {
            messageService.deleteMessage(message)
        }

    }

}