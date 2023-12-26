package de.wienecke.basicsecurityapp.controller.user

import de.wienecke.basicsecurityapp.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.util.*

@RestController
@RequestMapping("/api/users")
class UserController(
    private val userService: UserService
) {

    @PostMapping("/")
    fun createUser(@RequestBody userRequest: UserRequest): UserResponse? {
        return userService.createUser(userRequest.toUser())?.toResponse() ?: throw ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "User already exists"
        )
    }

    @GetMapping("/")
    fun listAllUsers(): List<UserResponse> {
        return userService.findAll().map {
            it.toResponse()
        }
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: UUID): UserResponse {
        return userService.findById(id)?.toResponse() ?: throw ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "User not found"
        )
    }

    @DeleteMapping("/{id}")
    fun deleteUserById(@PathVariable id: UUID): ResponseEntity<Boolean> {
        println("deleteUserById $id")

        if (!userService.deleteById(id)) {
            println("deleteUserById $id not found")

            throw ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "User not found"
            )
        }

        return ResponseEntity.ok(true)
    }
}