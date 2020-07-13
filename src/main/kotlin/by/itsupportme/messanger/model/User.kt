package by.itsupportme.messanger.model

import javax.persistence.*

@Entity
@Table(name = "users")
class User(
        @Column(name = "username")
        val username: String,

        @Column(name = "first_name")
        var firstName: String? = null,

        @Column(name = "last_name")
        val lastName: String? = null,

        @Column(name = "password")
        var password: String = "",

        @ManyToMany(fetch = FetchType.EAGER)
        var roles: MutableList<Role> = mutableListOf()
) : BaseEntity()