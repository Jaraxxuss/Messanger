package by.itsupportme.messanger.services

import by.itsupportme.messanger.model.User

interface UserService {
        fun register(user: User): User?
        fun findByUsername(username: String?): User?
        fun findById(id: Long): User?
        fun deleteById(id: Long)
        fun findAll() : Iterable<User>
}