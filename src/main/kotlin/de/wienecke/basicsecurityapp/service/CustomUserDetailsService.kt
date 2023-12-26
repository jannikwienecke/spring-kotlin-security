package de.wienecke.basicsecurityapp.service

import de.wienecke.basicsecurityapp.repository.UserRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

typealias ApplicationUser = de.wienecke.basicsecurityapp.model.User

@Service
class CustomUserDetailsService(
    private val userRepository: UserRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        return userRepository.findByEmail(username)?.toUserDetails() ?: throw Exception("User not found")
    }

    private fun ApplicationUser.toUserDetails(): UserDetails {
        return User.builder()
            .username(email)
            .password(password)
            .roles(role.name)
            .build()
    }
}