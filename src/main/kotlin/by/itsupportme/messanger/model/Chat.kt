package by.itsupportme.messanger.model

import javax.persistence.Entity
import javax.persistence.ManyToMany
import javax.persistence.OneToMany

@Entity
class Chat(
        @OneToMany
        val messages: MutableList<Message> = mutableListOf(),
        @ManyToMany
        val users: List<User>
) : NameEntity()