package de.wienecke.basicsecurityapp.config

import de.wienecke.basicsecurityapp.service.CustomUserDetailsService
import de.wienecke.basicsecurityapp.service.TokenService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetails
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val userDetailsService: CustomUserDetailsService,
    private val tokenService: TokenService
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeader: String? = request.getHeader("Authorization")

        if (authHeader === null) {
            filterChain.doFilter(request, response)
            return
        }

        val token = authHeader.extractToken()

        val doesNotContainBearerToken = authHeader.doesNotContainBearerToken()
        val isExpired = tokenService.isExpired(token)

        if (doesNotContainBearerToken || isExpired) {
            filterChain.doFilter(request, response)
            return
        }

        val email = tokenService.extractEmail(token)

        if (email !== null && SecurityContextHolder.getContext().authentication === null) {
            val userDetails = userDetailsService.loadUserByUsername(email)

            if (tokenService.isValid(token, userDetails)) {
                updateContext(userDetails, request)
            }

            filterChain.doFilter(request, response)
        } else {
            filterChain.doFilter(request, response)
        }


    }

    private fun updateContext(userDetails: UserDetails, request: HttpServletRequest) {
        val authToken = UsernamePasswordAuthenticationToken(
            userDetails,
            null,
            userDetails.authorities
        ).apply {
            details = WebAuthenticationDetails(request)
        }

        SecurityContextHolder.getContext().authentication = authToken
    }

    private fun String?.doesNotContainBearerToken(): Boolean =
        this === null || !this.startsWith("Bearer ")

    private fun String.extractToken(): String =
        this.substringAfter("Bearer ")
}