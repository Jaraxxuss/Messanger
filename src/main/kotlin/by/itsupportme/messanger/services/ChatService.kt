package by.itsupportme.messanger.services

import by.itsupportme.messanger.model.Chat
import by.itsupportme.messanger.model.Message
import by.itsupportme.messanger.model.User

interface ChatService {
    fun createChat(users:List<User>)
    fun addMessage(chat: Chat, message: Message)
}