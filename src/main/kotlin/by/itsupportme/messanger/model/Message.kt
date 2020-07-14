package by.itsupportme.messanger.model

import javax.persistence.Entity
import javax.persistence.OneToOne

@Entity
class Message(
        @OneToOne
        val sender: User,

        var content: String
) : BaseEntity()