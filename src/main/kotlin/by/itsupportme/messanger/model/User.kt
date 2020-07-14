package by.itsupportme.messanger.model

import com.fasterxml.jackson.annotation.JsonIgnore
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
        @JsonIgnore
        var password: String = "",

        @ManyToMany(fetch = FetchType.EAGER)
        @JsonIgnore
        var roles: MutableList<Role> = mutableListOf()
) : BaseEntity()