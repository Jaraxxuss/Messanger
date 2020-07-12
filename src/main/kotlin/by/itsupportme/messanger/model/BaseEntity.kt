package by.itsupportme.messanger.model

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.sql.Date
import javax.persistence.*

@MappedSuperclass
open class BaseEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @CreatedDate
        val created: Date? = null,

        @LastModifiedDate
        val updated: Date? = null,

        @Enumerated(EnumType.STRING)
        var status: Status? = null
        )