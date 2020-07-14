package by.itsupportme.messanger.model

import javax.persistence.*

@Entity
@Table(name = "roles")
class Role(
        @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
        val users: List<User>
) : NameEntity()