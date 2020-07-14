package by.itsupportme.messanger.model

import javax.persistence.MappedSuperclass

@MappedSuperclass
open class NameEntity(
        val name: String = ""
) : BaseEntity()