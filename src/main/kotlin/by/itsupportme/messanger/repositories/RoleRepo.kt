package by.itsupportme.messanger.repositories

import by.itsupportme.messanger.model.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoleRepo : JpaRepository<Role, Long> {
        fun findByName(name: String) : Role
}