package by.itsupportme.messanger.services.impl

import by.itsupportme.messanger.model.Status
import by.itsupportme.messanger.model.User
import by.itsupportme.messanger.repositories.RoleRepo
import by.itsupportme.messanger.repositories.UserRepo
import by.itsupportme.messanger.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
        @Autowired
        val userRepo: UserRepo,
        @Autowired
        val roleRepo: RoleRepo,
        @Autowired
        val bCryptPasswordEncoder: BCryptPasswordEncoder
): UserService {
        override fun register(user: User): User {
                val userRole = roleRepo.findByName("ROLE_USER")
                user.password = bCryptPasswordEncoder.encode(user.password)
                user.roles.add(userRole)
                user.status = Status.ACTIVE

                return userRepo.save(user)

        }

        override fun findByUsername(username: String?): User? {
                return userRepo.findByUsername(username)
        }

        override fun findById(id: Long): User? {
                return userRepo.findById(id).orElse(null)
        }

        override fun deleteById(id: Long) {
                userRepo.deleteById(id)
        }

        override fun findAll(): Iterable<User> {
                return userRepo.findAll()
        }
}