package by.itsupportme.messanger.repositories

import by.itsupportme.messanger.model.Chat
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ChatRepo : JpaRepository<Chat, Long> {
    fun findByName(name: String): Chat
}