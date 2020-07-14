package by.itsupportme.messanger.repositories

import by.itsupportme.messanger.model.Message
import by.itsupportme.messanger.model.Status
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MessageRepo : JpaRepository<Message, Long> {
    fun findAllByStatus(status: Status = Status.ACTIVE): Iterable<Message>
}