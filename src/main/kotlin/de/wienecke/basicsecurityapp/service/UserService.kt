package de.wienecke.basicsecurityapp.service

import de.wienecke.basicsecurityapp.model.User
import de.wienecke.basicsecurityapp.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository
) {
    fun findByEmail(email: String) = userRepository.findByEmail(email)

    fun findById(id: UUID) = userRepository.findById(id)

    fun createUser(user: User): User? {
        val hasUser = userRepository.findByEmail(user.email)

        return if (hasUser == null) {
            return userRepository.save(user)
        } else null
    }

    fun deleteById(id: UUID) = userRepository.deleteById(id)

    fun findAll() = userRepository.findAll()


}