package by.itsupportme.messanger.services

import by.itsupportme.messanger.model.Message

interface MessageService {
    fun createMessage(message: Message)
    fun findAllActive(): Iterable<Message>
    fun findById(id: Long): Message?
    fun updateMessage(message: Message)
    fun deleteMessage(message: Message)
}