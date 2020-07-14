package by.itsupportme.messanger.services.impl

import by.itsupportme.messanger.model.Chat
import by.itsupportme.messanger.model.Message
import by.itsupportme.messanger.model.User
import by.itsupportme.messanger.repositories.ChatRepo
import by.itsupportme.messanger.services.ChatService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ChatServiceImpl(
        @Autowired
        val chatRepo: ChatRepo
) : ChatService {
    override fun createChat(users: List<User>) {
        val chat = Chat(users = users)
        chatRepo.save(chat)
    }

    override fun addMessage(chat: Chat, message: Message) {
        chat.messages.add(message)
        chatRepo.save(chat)
    }

}