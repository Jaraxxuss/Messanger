package by.itsupportme.messanger.services.impl

import by.itsupportme.messanger.model.Message
import by.itsupportme.messanger.model.Status
import by.itsupportme.messanger.repositories.MessageRepo
import by.itsupportme.messanger.services.MessageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MessageServiceImpl(
        @Autowired
        val messageRepo: MessageRepo
) : MessageService {
    override fun createMessage(message: Message) {
        messageRepo.save(message)
    }

    override fun findAllActive() = messageRepo.findAllByStatus()

    override fun findById(id: Long): Message? = messageRepo.findById(id).orElse(null)

    override fun updateMessage(message: Message) {
        messageRepo.save(message)
    }

    override fun deleteMessage(message: Message) {
        message.status = Status.DELETED
        updateMessage(message)
    }


}