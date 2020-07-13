package by.itsupportme.messanger.security.jwt

import by.itsupportme.messanger.model.Role
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.PropertySource
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import java.util.*
import javax.servlet.http.HttpServletRequest

@Component
class JwtTokenProvider(
        @Value("\${jwt.token.secret}")
        val secret: String,
        @Value("\${jwt.token.expired}")
        val expired: Long,
        @Autowired
        @Qualifier("jwtUserDetailsService")
        val userDetailsService: UserDetailsService

) {
        fun createToken(username: String, roles: List<Role>): String {
                val claims = Jwts.claims().setSubject(username)
                claims["roles"] = getRoleNames(roles)
                val now = Date()
                val validity = Date(now.time + expired)
                return Jwts.builder()
                        .setClaims(claims)
                        .setIssuedAt(now)
                        .setExpiration(validity)
                        .signWith(SignatureAlgorithm.HS256, secret)
                        .compact()
        }

        fun getAuthentication(token: String): Authentication? {
                val userDetails = userDetailsService.loadUserByUsername(getUsername(token))
                return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
        }

        fun getUsername(token: String): String {
                return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).body.subject
        }

        fun validateToken(token: String): Boolean {
                try {
                        val claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token)
                        return !claims.body.expiration.before(Date())
                } catch (e: JwtException) {
                        throw JwtAuthenticationException("JWT token is expired or invalid")
                } catch (e: IllegalArgumentException) {
                        throw JwtAuthenticationException("JWT token is expired or invalid")
                }

        }

        fun getRoleNames(userRoles: List<Role>): List<String> {
                val roles = mutableListOf<String>()
                for (userRole in userRoles) {
                        roles.add(userRole.name)
                }

                return roles
        }

        fun resolveToken(req: HttpServletRequest): String? {
                val bearerToken = req.getHeader("Authorization")
                if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
                        return bearerToken.substring(7)
                }

                return null
        }
}