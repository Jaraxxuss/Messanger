package by.itsupportme.messanger.model

import javax.persistence.*

@Entity
@Table(name = "roles")
class Role(
        @Column(name = "name")
        val name: String,

        @ManyToMany(mappedBy = "roles",fetch = FetchType.LAZY)
        val users: List<User>
) : BaseEntity()