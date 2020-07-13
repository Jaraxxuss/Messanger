package by.itsupportme.messanger.repositories

import by.itsupportme.messanger.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepo : JpaRepository<User, Long> {
        fun findByUsername(username: String?): User?
        fun existsUserByUsername(username: String?): Boolean
}