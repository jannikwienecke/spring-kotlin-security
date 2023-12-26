package de.wienecke.basicsecurityapp.controller.user

import de.wienecke.basicsecurityapp.model.Role
import de.wienecke.basicsecurityapp.model.User
import java.util.*

data class UserRequest(
    val email: String,
    val password: String,
)

fun UserRequest.toUser() = User(
    email = email,
    password = password,
    role = Role.USER,
    id = UUID.randomUUID(),
)

fun User.toResponse() = UserResponse(
    id = id,
    email = email,
)