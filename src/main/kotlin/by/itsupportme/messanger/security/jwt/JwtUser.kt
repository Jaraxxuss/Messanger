package by.itsupportme.messanger.security.jwt

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.Date

class JwtUser(
        private val userName: String,
        private val pass: String,
        private val myAuthorities: MutableCollection<out GrantedAuthority>,
        private val enabled: Boolean?

) : UserDetails {
        override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
                return myAuthorities
        }

        override fun isEnabled(): Boolean {
                return this.enabled!!
        }

        override fun getUsername(): String {
                return userName
        }

        override fun isCredentialsNonExpired(): Boolean {
                return true
        }

        override fun getPassword(): String {
                return pass
        }

        override fun isAccountNonExpired(): Boolean {
                return true
        }

        override fun isAccountNonLocked(): Boolean {
                return true
        }
}