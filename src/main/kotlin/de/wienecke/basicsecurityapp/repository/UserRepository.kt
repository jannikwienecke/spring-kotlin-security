package de.wienecke.basicsecurityapp.repository

import de.wienecke.basicsecurityapp.model.Role
import de.wienecke.basicsecurityapp.model.User
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class UserRepository(
    private val encoder: PasswordEncoder
) {

    private val users = mutableListOf<User>(
        User(
            id = UUID.randomUUID(),
            email = "user@test.de",
            password = encoder.encode("password"),
            role = Role.USER
        ),
        User(
            id = UUID.randomUUID(),
            email = "admin@test.de",
            password = encoder.encode("password"),
            role = Role.ADMIN
        ),
    )

    fun findByEmail(email: String): User? {
        return users.find {
            it.email == email
        }
    }

    fun findById(id: UUID): User? {
        return users.find {
            it.id == id
        }
    }

    fun save(user: User): Boolean {
        val updatedUser = user.copy(
            password = encoder.encode(user.password)
        )
        return users.add(updatedUser)
    }

    fun deleteById(id: UUID): Boolean {
        return users.removeIf {
            it.id == id

        }


    }

    fun findAll() = users
}